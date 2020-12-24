package com.example.mvvmdagger.data.network

import com.example.mvvmdagger.data.model.Feed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("/random")
    fun fetchFeed(): Call<Feed?>?



}