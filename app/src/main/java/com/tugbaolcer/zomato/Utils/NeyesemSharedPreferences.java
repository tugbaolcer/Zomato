package com.tugbaolcer.zomato.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class NeyesemSharedPreferences {
    public static String BaseUrl = "https://developers.zomato.com/api/v2.1/";
    public static String UserKey = " 9da36d3bc8d0820b0ec07ad58255f204";


    public static void initSharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("NeyesemDB", Context.MODE_PRIVATE);
        BaseUrl = sharedPreferences.getString("BaseUrl", BaseUrl);
        UserKey = sharedPreferences.getString("UserKey", UserKey);
    }
}