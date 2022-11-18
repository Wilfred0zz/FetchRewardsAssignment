package com.example.fetchrewardsassignment

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsassignment.model.Item
import com.example.fetchrewardsassignment.model.ItemAdapter
import com.example.fetchrewardsassignment.retofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = mutableListOf<Item>()
        recyclerView = findViewById(R.id.recyclerview)
        itemAdapter = ItemAdapter(this, items)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter


        val RetroFitCall = Retrofit.create().getItems() //calls API to get JSON data

        RetroFitCall.enqueue( object : Callback<List<Item>>{
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) { //Callback function that handles response given from API call
                Log.i(TAG, "Successfully  fetched data")
                if(response.body() != null){
                    val filterDesc = response.body()!!
                    val filteredList = filterDesc.filter {  item -> !item.name.isNullOrBlank() }.sortedByDescending{item -> item.id}.sortedByDescending { item -> item.listId }.asReversed()
                    items.addAll(filteredList)//add items filtered out for empty or blank names and groups by first ItemId descending and then name Id descending
                    itemAdapter.notifyDataSetChanged()
                }else{
                    Log.e(TAG, "Response Body is null")
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Log.e(TAG, "onFailure $t")
            }
        })


    }



}