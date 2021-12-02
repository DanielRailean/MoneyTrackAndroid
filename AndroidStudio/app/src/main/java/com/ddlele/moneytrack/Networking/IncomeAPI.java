package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Wrappers.Income;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IncomeAPI {
    @POST("income")
    Call<Income> create(@Body Income item);
    @GET("income/user")
    Call<List<Income>> getAll();
    @GET("income/{id}")
    Call<Income> get(@Path("Id") long id);
    @PUT("income")
    Call<Income> update(@Body Income item);
    @DELETE
    Call<Income> delete(@Path("Id") long id);


}
