package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Wrappers.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountAPI {
    @POST("account")
    Call<Account> create(@Body Account item);
    @POST("account/user")
    Call<List<Account>> getAll();
    @GET("account/{id}")
    Call<Account> get(@Path("Id") long id);
    @PUT("account")
    Call<Account> update(@Body Account item);
    @DELETE("account/{id}")
    Call<Account> delete(@Path("id") long id);


}
