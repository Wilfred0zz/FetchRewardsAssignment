package com.example.fetchrewardsassignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit{

  var BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

        fun create() : APIService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIService::class.java)

        }
}