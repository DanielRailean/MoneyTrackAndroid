package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Wrappers.Currency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CurrencyAPI {
    @POST("currency")
    Call<Currency> create(@Body Currency item);
    @GET("currency/user")
    Call<List<Currency>> getAll();
    @GET("currency/{id}")
    Call<Currency> get(@Path("Id") long id);
    @PUT("currency")
    Call<Currency> update(@Body Currency item);
    @DELETE
    Call<Currency> delete(@Path("Id") long id);


}
