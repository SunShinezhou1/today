package com.example.my_shopping2.activity.MyInterface;

import com.example.my_shopping2.activity.bean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface javabeanservice {
//    @GET("HOME_URL.json")
    Call<bean> getresult();
}
