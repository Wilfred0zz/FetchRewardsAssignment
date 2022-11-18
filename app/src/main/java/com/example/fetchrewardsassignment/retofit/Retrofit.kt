package com.example.fetchrewardsassignment.retofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit{ //API  that takes the URL that will be used to pull JSON data and Convert it to list<item>to be used by app

  var BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

        fun create() : APIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIService::class.java)

        }
}