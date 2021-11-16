package com.developer.dollarkursiapp

import adapters.MyAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.developer.dollarkursiapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import models.DollarClass

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var requestQueue: RequestQueue
    private lateinit var myAdapter: MyAdapter
    private val uri = "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestQueue = Volley.newRequestQueue(this)
        VolleyLog.DEBUG = true   //qanday ma'lumot kelayotganini Logda ko'rsatib turadi

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, uri, null,
            { response ->
                val type = object : TypeToken<List<DollarClass>>() {}.type
                val list = Gson().fromJson<List<DollarClass>>(response.toString(), type)

                myAdapter = MyAdapter(list)
                binding.recycleView.adapter = myAdapter
            }
        ) { error -> Toast.makeText(this@MainActivity, error?.message, Toast.LENGTH_SHORT).show() }
        requestQueue.add(jsonArrayRequest)
    }
}