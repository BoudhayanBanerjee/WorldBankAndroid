package com.example.ronnie.WorldBankIndicators;


import java.util.Map;
import java.util.TreeMap;
/**
 * Created by Ronnie on 6/24/2016.
 */
public class indicatorCodes {
    final Map<String, String> map = new TreeMap<String, String> (String.CASE_INSENSITIVE_ORDER);
    public indicatorCodes() {
        map.put("GDP growth (annual %)", "NY.GDP.MKTP.KD.ZG");
        map.put("GDP (current US$)","NY.GDP.MKTP.CD");
        map.put("GDP per capita (USD)","NY.GDP.PCAP.CD");
        map.put("GNI per capita, Atlas method (USD)","NY.GNP.ATLS.CD");
        map.put("Exports of goods and services (% of GDP)","NE.EXP.GNFS.ZS");
        map.put("Foreign direct investment, net inflows (Billion USD)","BX.KLT.DINV.CD.WD");
        map.put("GNI per capita, PPP (International $)","NY.GNP.PCAP.PP.CD");
        map.put("GINI index","SI.POV.GINI");
        map.put("Inflation consumer prices %","FP.CPI.TOTL.ZG");
        map.put("Population (Million)","SP.POP.TOTL");
        map.put("Life expectancy at birth (years)","SP.DYN.LE00.IN");
        map.put("Internet users %","IT.NET.USER.P2");
        map.put("Imports of goods (% of GDP)","NE.IMP.GNFS.ZS");
        map.put("Unemployment %","SL.UEM.TOTL.ZS");
        map.put("Agriculture (% GDP)","NV.AGR.TOTL.ZS");
        map.put("CO2 emissions (metric tons per capita)","EN.ATM.CO2E.PC");
        map.put("Central government debt (% GDP)","GC.DOD.TOTL.GD.ZS");
        map.put("Inflation %","FP.CPI.TOTL.ZG");
        map.put("Poverty %","SI.POV.NAHC");

    }
    public String getIndicator(String indicator){
        String indicatorFound = map.get(indicator);
        if(indicatorFound==null){
            indicatorFound="";
        }
        return indicatorFound;
    }
}
