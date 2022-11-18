package com.example.fetchrewardsassignment

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var items = mutableListOf<Item>()
        recyclerView = findViewById(R.id.recyclerview)
        itemAdapter = ItemAdapter(this, items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter


        val RetroFitCall = Retrofit.create().getItems()

        RetroFitCall.enqueue( object : Callback<List<Item>>{
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    Log.i(TAG, "Successfully  fetched data")
                val filterDesc = response.body()!!
                val filterName = filterDesc.filter {  item -> !item.name.isNullOrBlank() }.sortedByDescending{item -> item.id}.sortedByDescending { item -> item.listId }.asReversed()

                items.addAll(filterName)
                itemAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Log.e(TAG, "onFailure $t")
            }
        })


    }



}