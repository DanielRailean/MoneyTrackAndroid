package com.ddlele.moneytrack.Networking;

import com.ddlele.moneytrack.Wrappers.ApiResponses.JWT;
import com.ddlele.moneytrack.Wrappers.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("user/login")
    Call<JWT> login(@Body User user);
    @POST("user/register")
    Call<User> register(@Body User user);
}
