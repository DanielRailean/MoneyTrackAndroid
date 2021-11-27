package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Util.LocalStorage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    static Gson g =  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss.S").create();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://money-api.ddlele.com")
            .addConverterFactory(GsonConverterFactory.create(g));


    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + LocalStorage.getInstance().get("access_token"))
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    private static Retrofit retrofit = retrofitBuilder.build();
    private static Retrofit retrofitAuth = retrofitBuilder.client(client).build();

    private static UserAPI userAPI = retrofit.create(UserAPI.class);
    private static ExpenseAPI expenseAPI = retrofitAuth.create(ExpenseAPI.class);

    public static UserAPI getUserAPI (){
        return userAPI;
    }

    public static ExpenseAPI getExpenseAPI() {
        return expenseAPI;
    }
}
