package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Wrappers.Expense;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExpenseAPI {
    @POST("expense")
    Call<Expense> create(@Body Expense item);
    @GET("expense/user")
    Call<List<Expense>> getAll();
    @GET("expense/{id}")
    Call<Expense> get(@Path("Id") long id);
    @PUT("expense")
    Call<Expense> update(@Body Expense item);
    @DELETE
    Call<Expense> delete(@Path("Id") long id);


}
