package com.example.fetchrewardsassignment

import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("hiring.json")
     fun getItems(): Call<List<Item>>

}