package com.example.webapi.activity.api;

import com.example.webapi.activity.model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TaskApi {

    @FormUrlEncoded
    @POST("tasks")
    Call<Void>register_Task(@Header("Authorization")String header, @Field("name")String name);

    @GET("tasks")
    Call<List<Task>> getALLTask(@Header("Authorization")String header);

}
