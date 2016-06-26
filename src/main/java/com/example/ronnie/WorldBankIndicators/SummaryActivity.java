package com.example.ronnie.WorldBankIndicators;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SummaryActivity extends AppCompatActivity {

    static String country;
    static String indicator;
    static String start;
    static String end;

    TextView textViewIndicator;
    TextView textViewYear;
    TextView textViewValue;
    ListView list;
    ArrayList<HashMap<String, String>> valueList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        country = intent.getStringExtra(MainActivity.EXTRA_COUNTRY);
        indicator = intent.getStringExtra(MainActivity.EXTRA_INDICATOR);
        start = intent.getStringExtra(MainActivity.EXTRA_START);
        end = intent.getStringExtra(MainActivity.EXTRA_END);

        Log.d("START",start);
        Log.d("END",end);



        new RetrieveFeedTask().execute();
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;
        private ProgressDialog pDialog;

        protected void onPreExecute() {

            //responseView.setText("");
        }

        protected String doInBackground(Void...urls){

            try{
                URL url = new URL("http://api.worldbank.org/countries/"+country+"/indicators/"+indicator+"?date="+start+":"+end+"&format=json");

                Log.d("URL","http://api.worldbank.org/countries/"+country+"/indicators/"+indicator+"?date="+start+":"+end+"?format=json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try{
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }

            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }


        protected void onPostExecute(String response) {
            if(response == null) {
                response = "Houston,We have a problem";
                Context context = getApplicationContext();
                CharSequence text = response;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            //Log.i("INFO", response);

           try {
               JSONArray initArray = (JSONArray) new JSONTokener(response).nextValue();



               JSONArray dataArray = initArray.optJSONArray(1);


               for(int i=0;i<dataArray.length();i++){

                   JSONObject valueObject = dataArray.getJSONObject(i);


                   JSONObject indicatorObject = valueObject.getJSONObject("indicator");
                   String indicator = indicatorObject.getString("value");

                   indicator = new displayIndicators().getDisplayCode(indicator);

                   JSONObject countryObject= valueObject.getJSONObject("country");
                   String country = countryObject.getString("value");

                   String value = valueObject.getString("value");

                   if (value == "null")
                   {
                       value = "--";
                   }
                   else{
                       if (indicator == "GDP growth %"
                               || indicator == "GDP per capita (USD)"
                               || indicator == "Export (% GDP)"
                               || indicator == "Inflation %"
                               || indicator == "Life expectancy (years)"
                               || indicator == "Internet users %"
                               || indicator == "Import (% GDP)"
                               || indicator == "Unemployment %"
                               || indicator == "Agriculture (% GDP)"
                               || indicator == "CO2 emissions(metric ton)"
                               || indicator == "Debt (% GDP)"
                               || indicator == "Inflation %"
                               || indicator == "Poverty %"
                               )
                       {
                           float amount = Float.parseFloat(value);
                           value = String.format("%.2f", amount);
                       }
                       else if(indicator == "GDP (Billion USD)"
                               || indicator =="GNI Atlas(Billion USD)"
                               || indicator == "FDI inflow (Billion USD)"
                               ){
                           float amount = Float.parseFloat(value);
                           amount = amount / 1000000000;
                           value = String.format("%.2f", amount);
                       }
                       else if(indicator == "GNI per capita (USD)"
                               || indicator == "GINI index"

                               )
                       {
                           value = value;
                       }
                       else if(indicator=="Population (Million)"){
                           float amount = Float.parseFloat(value);
                           amount = amount / 1000000;
                           value = String.format("%.2f", amount);
                       }

                   }
                   String year = valueObject.getString("date");


                   if(value != "--") {

                       HashMap<String, String> map = new HashMap<String, String>();
                       map.put("indicator", indicator.toUpperCase());
                       map.put("year", year);
                       map.put("value", value);
                       map.put("country", country);

                       valueList.add(map);


                       list = (ListView) findViewById(R.id.list);

                       ListAdapter adapter = new SimpleAdapter(SummaryActivity.this,
                               valueList,
                               R.layout.custom_row,
                               new String[]{"indicator", "year", "value", "country"},
                               new int[]{R.id.textViewIndicator, R.id.textViewYear, R.id.textViewValue, R.id.textViewCountry}
                       );
                       list.setAdapter(adapter);
                   }

               }


            } catch (JSONException e) {
               e.printStackTrace();
               response = "Houston,We have a problem";
               Context context = getApplicationContext();
               CharSequence text = "Houston,We have a problem";
               int duration = Toast.LENGTH_SHORT;



               Toast toast = Toast.makeText(context, text, duration);
               toast.show();

           }
        }
    }


}
