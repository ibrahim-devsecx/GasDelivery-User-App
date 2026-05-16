package com.ibm.gasapp.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientFCM {

    public static final String API_BASE_URL = "https://fcm.googleapis.com/";

    private static Retrofit instanceRetrofit;

    public static final String fcmServerKey ="AAAAmZ40eyc:APA91bFePWKwnxY1ZggK1r1GRQta5d9ZAY5ze1oApQzB_HEDryGwxitmwJJ8M1xQBxzaTNBpLcjppQaOiA36-9-pLtDRLXhcXL-QkaCjfGDoSwZ7tS1flP1g-D5FQov-M5K9-ny2Tp0b";


    public static synchronized Retrofit getInstanceRetrofit() {
        if (instanceRetrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            instanceRetrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            return instanceRetrofit;
        } else {
            return instanceRetrofit;

        }
    }
}
