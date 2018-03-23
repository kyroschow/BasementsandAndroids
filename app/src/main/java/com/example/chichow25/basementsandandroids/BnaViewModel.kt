package com.example.chichow25.basementsandandroids

import android.arch.lifecycle.ViewModel
import com.example.chichow25.basementsandandroids.gamedata.models.Armor
import com.example.chichow25.basementsandandroids.gamedata.models.Weapon
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

    @GET("equipment/{index}")
    fun getWeaponAt(@Path("index") index: Int): Call<Weapon>

    @GET("equipment/{index}")
    fun getArmorAt(@Path("index") index: Int): Call<Armor>
}