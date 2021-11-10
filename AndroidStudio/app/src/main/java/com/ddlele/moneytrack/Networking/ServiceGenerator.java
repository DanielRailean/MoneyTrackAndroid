package com.ddlele.moneytrack.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://money-api.ddlele.com")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static UserAPI userAPI = retrofit.create(UserAPI.class);

    public static UserAPI getUserAPI (){
        return userAPI;
    }
}
