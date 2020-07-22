package com.example.webapi.activity.api;

import com.example.webapi.activity.model.User;
import com.example.webapi.activity.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserApi {

   @POST("user/register")

   Call<UserResponse> registerUser(@Body User user);
   @FormUrlEncoded
   @POST("user/login")
    Call<UserResponse>checkUser(@Field("username")String username,@Field("password")String password);
}
