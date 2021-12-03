package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Wrappers.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryAPI {
    @POST("category")
    Call<Category> create(@Body Category item);
    @GET("category/user")
    Call<List<Category>> getAll();
    @GET("category/{id}")
    Call<Category> get(@Path("Id") long id);
    @PUT("category")
    Call<Category> update(@Body Category item);
    @DELETE("category/{id}")
    Call<Category> delete(@Path("id") long id);


}
