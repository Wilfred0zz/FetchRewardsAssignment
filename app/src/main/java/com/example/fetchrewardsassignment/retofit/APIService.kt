package com.example.fetchrewardsassignment.retofit

import com.example.fetchrewardsassignment.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface APIService { //API interface where can add get, post and put Api calls.
    @GET("hiring.json")
     fun getItems(): Call<List<Item>>

}