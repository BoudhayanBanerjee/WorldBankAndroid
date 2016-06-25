package com.example.ronnie.WorldBankIndicators;

import java.util.Map;
import java.util.TreeMap;
public class displayIndicators {

    final Map<String, String> map = new TreeMap<String, String> (String.CASE_INSENSITIVE_ORDER);
    public displayIndicators() {
        map.put("GDP growth (annual %)", "GDP growth %");
        map.put("GDP at market prices (current US$)","GDP (Billion USD)");
        map.put("GDP per capita (current US$)","GDP per capita (USD)");
        map.put("GNI, Atlas method (current US$)","GNI Atlas(Billion USD)");
        map.put("Exports of goods and services (% of GDP)","Export (% GDP)");
        map.put("Foreign direct investment, net inflows (BoP, current US$)","FDI inflow (Billion USD)");
        map.put("GNI per capita, PPP (current international $)","GNI per capita (USD)");
        map.put("GINI index (World Bank estimate)","GINI index");
        map.put("Inflation, consumer prices (annual %)","Inflation %");
        map.put("Population, total","Population (Million)");
        map.put("Life expectancy at birth, total (years)","Life expectancy (years)");
        map.put("Internet users (per 100 people)","Internet users %");
        map.put("Imports of goods and services (% of GDP)","Import (% GDP)");
        map.put("Unemployment, total (% of total labor force)","Unemployment %");
        map.put("Agriculture, value added (% of GDP)","Agriculture (% GDP)");
        map.put("CO2 emissions (metric tons per capita)","CO2 emissions(metric ton)");
        map.put("Central government debt, total (% of GDP)","Debt (% GDP)");
        map.put("Inflation, consumer prices (annual %)","Inflation %");
        map.put("Poverty headcount ratio at national poverty lines (% of population)","Poverty %");
    }
    public String getDisplayCode(String indicator){
        String indicatorFound = map.get(indicator);
        if(indicatorFound==null){
            indicatorFound="";
        }
        return indicatorFound;
    }
}
