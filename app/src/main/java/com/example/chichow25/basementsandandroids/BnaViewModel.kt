package com.example.chichow25.basementsandandroids

import android.arch.lifecycle.ViewModel
import io.realm.Realm
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by chichow25 on 3/19/18.
 */
class BnaViewModel : ViewModel(), DndApi by Retrofit.Builder()
        .baseUrl(DndApi.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DndApi::class.java) {

    private val realm = Realm.getDefaultInstance()


}

private interface DndApi {

    companion object {
        val baseURL = "http://dnd5eapi.co/api/"
    }
}