package com.tugbaolcer.zomato.Network;

public class ApiUtils {
    public static final String BASE_URL="https://developers.zomato.com/api//v2.1/";
    public static ZomataDaoInterface getZomatoDaoInterface(){
       return RetrofitClient.getClient(BASE_URL).create(ZomataDaoInterface.class) ;
    }
}
