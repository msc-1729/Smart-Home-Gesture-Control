package com.example.assignment2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ServerConnection {
    private static String BASE_URL = "http://192.168.0.136:5000/";
    static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ServerConnection.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

