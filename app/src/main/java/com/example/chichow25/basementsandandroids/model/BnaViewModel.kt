package com.example.chichow25.basementsandandroids.model

import android.arch.lifecycle.ViewModel
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Armor
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Weapon
import com.example.chichow25.basementsandandroids.repo.retrofit.DndApi
import io.realm.Realm
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by chichow25 on 3/19/18.
 */
class BnaViewModel : ViewModel(), DndApi by DndApi.create() {

    private val realm = Realm.getDefaultInstance()
}