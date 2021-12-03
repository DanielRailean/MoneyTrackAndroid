package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Wrappers.Transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TransferAPI {
    @POST("transfer")
    Call<Transfer> create(@Body Transfer item);
    @GET("transfer/user")
    Call<List<Transfer>> getAll();
    @GET("transfer/{id}")
    Call<Transfer> get(@Path("Id") long id);
    @PUT("transfer")
    Call<Transfer> update(@Body Transfer item);
    @DELETE("transfer/{id}")
    Call<Transfer> delete(@Path("id") long id);


}
