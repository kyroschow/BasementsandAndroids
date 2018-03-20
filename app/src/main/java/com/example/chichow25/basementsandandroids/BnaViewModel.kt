package com.example.chichow25.basementsandandroids

import android.arch.lifecycle.ViewModel
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

/**
 * Created by chichow25 on 3/19/18.
 */
class BnaViewModel : ViewModel() {

    private val realm = Realm.getDefaultInstance()
    private val dndApi = Retrofit.Builder()
            .baseUrl(DndApi.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DndApi::class.java)

    private interface DndApi {

        companion object {
            val baseURL = "http://dnd5eapi.co/api/"
        }
    }
}