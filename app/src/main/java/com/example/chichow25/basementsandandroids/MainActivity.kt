package com.example.chichow25.basementsandandroids

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val dndApi : DndApi by lazy {
        val temp = Retrofit.Builder()
                .baseUrl(DndApi.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DndApi::class.java)
        temp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //test
    }
}