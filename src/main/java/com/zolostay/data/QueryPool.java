package com.zolostay.data;

import org.testng.annotations.DataProvider;

import java.time.LocalDate;
import java.util.HashMap;

public class QueryPool {

    public static LocalDate currentDate = LocalDate.now();

    @DataProvider(name = "feedData")
    public static Object[][] feedData() {

        HashMap<String, String> data1 = new HashMap<>();
        data1.put("param1","zolostays");
        data1.put("param2","3");
        data1.put("param3","4");
        data1.put("param4","2");
        data1.put("param5","3");

        HashMap<String, String> data2 = new HashMap<>();
        data2.put("param1","Best PG in India");
        data2.put("param2","1");
        data2.put("param3","2");
        data2.put("param4","5");
        data2.put("param5","6");

        HashMap<String, String> data3 = new HashMap<>();
        data3.put("param1","Managed PG");
        data3.put("param2","19");
        data3.put("param3","3");
        data3.put("param4","3");
        data3.put("param5","2");

        HashMap<String, String> data4 = new HashMap<>();
        data4.put("param1","Zolo Career");
        data4.put("param2","2");
        data4.put("param3","5");
        data4.put("param4","2");
        data4.put("param5","1");


        return new Object[][]{ {data1},{data2},{data3},{data4}};
    }



}