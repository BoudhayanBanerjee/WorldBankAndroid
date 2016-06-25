package com.example.ronnie.WorldBankIndicators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_COUNTRY ="com.example.ronnie.WorldBankIndicators.COUNTRY";
    public final static String EXTRA_INDICATOR ="com.example.ronnie.WorldBankIndicators.INDICATOR";
    public final static String EXTRA_START ="com.example.ronnie.WorldBankIndicators.START";
    public final static String EXTRA_END ="com.example.ronnie.WorldBankIndicators.END";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*code added for spinners*/

        Spinner spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapterCountry = ArrayAdapter.createFromResource(this,
                        R.array.countries_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCountry.setAdapter(adapterCountry);

        Spinner spinnerIndicator = (Spinner) findViewById(R.id.spinnerIndicator);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterIndicator = ArrayAdapter.createFromResource(this,
                R.array.indicator_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterIndicator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerIndicator.setAdapter(adapterIndicator);


        Spinner spinnerStart = (Spinner) findViewById(R.id.spinnerStart);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterStart = ArrayAdapter.createFromResource(this,
                R.array.yearStart_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerStart.setAdapter(adapterStart);


        Spinner spinnerEnd = (Spinner) findViewById(R.id.spinnerEnd);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterEnd = ArrayAdapter.createFromResource(this,
                R.array.yearEnd_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterEnd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerEnd.setAdapter(adapterEnd);

        /*+------------   country selection --------------+*/
        /*
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {
                }else {
                    // Your code to process the selection

                    String country = spinnerCountry.getSelectedItem().toString();
                    country = new countryCodes().getCode(country);
                    TextView textView =(TextView) findViewById(R.id.textView);
                    textView.setText(url1+country);

                }
            }
        });*/


         /*+------------   indicator selection --------------+*/
       /* spinnerIndicator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {
                }else {
                    // Your code to process the selection
                    String indicator = spinnerIndicator.getSelectedItem().toString();
                    indicator = new indicatorCodes().getIndicator(indicator);

                    TextView textView =(TextView) findViewById(R.id.textView);
                    textView.append(url2+indicator);

                }
            }
        });*/


         /*+------------   start year selection --------------+*/
        /*spinnerStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {



                }else {
                    // Your code to process the selection
                    String startYear = spinnerStart.getSelectedItem().toString();
                    TextView textView =(TextView) findViewById(R.id.textView);
                    textView.append(url3+startYear);

                }
            }
        });*/

          /*+------------   end year selection --------------+*/
        /*spinnerEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {



                }else {
                    // Your code to process the selection
                    String endYear = spinnerEnd.getSelectedItem().toString();

                    TextView textView =(TextView) findViewById(R.id.textView);
                    textView.append(url5+endYear+url4);

                }
            }
        });*/

    }


    public void sendAPICall(View view) {
        Intent intent = new Intent(this, SummaryActivity.class);
        //TextView textView = (TextView) findViewById(R.id.textView);

        Spinner spinnerCountry =(Spinner) findViewById(R.id.spinnerCountry);
        String country = spinnerCountry.getSelectedItem().toString();

        Spinner spinnerIndicator =(Spinner) findViewById(R.id.spinnerIndicator);
        String indicator = spinnerIndicator.getSelectedItem().toString();

        Spinner spinnerStart =(Spinner) findViewById(R.id.spinnerStart);
        String start = spinnerStart.getSelectedItem().toString();

        Spinner spinnerEnd =(Spinner) findViewById(R.id.spinnerEnd);
        String end = spinnerEnd.getSelectedItem().toString();

        int pos = spinnerCountry.getSelectedItemPosition();
        pos = spinnerIndicator.getSelectedItemPosition();
        pos = spinnerStart.getSelectedItemPosition();
        pos = spinnerEnd.getSelectedItemPosition();

        //Log.d("POS",Integer.toString(pos));


        if(pos != 0){


            country = new countryCodes().getCode(country);
            indicator = new indicatorCodes().getIndicator(indicator);
            intent.putExtra(EXTRA_COUNTRY, country);
            intent.putExtra(EXTRA_INDICATOR, indicator);
            intent.putExtra(EXTRA_START, start);
            intent.putExtra(EXTRA_END, end);
            startActivity(intent);
        }

    }



}
