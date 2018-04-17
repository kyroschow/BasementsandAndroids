package com.example.chichow25.basementsandandroids.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.chichow25.basementsandandroids.repo.gamedata.Monster
import com.example.chichow25.basementsandandroids.repo.retrofit.DndApi
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Armor
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Weapon
import com.example.chichow25.basementsandandroids.util.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DndApiViewModel(application: Application) : AndroidViewModel(application) {

    private val dndApi = Retrofit.Builder()
            .baseUrl(DndApi.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DndApi::class.java)
    val equipmentList = application.loadEquipmentCategoriesFromAssets()
    val monsterInfoList = application.loadMonsterInfosFromAssets()

    suspend fun getWeaponsAsync(): List<Weapon> = equipmentList[0].getEquipmentIndexes().map {
        dndApi.getWeaponAt(it).await()
    }

    suspend fun getArmourAsync(): List<Armor> = equipmentList[1].getEquipmentIndexes().map {
        dndApi.getArmourAt(it).await()
    }

    suspend fun getMonstersAsync() : List<Monster> = monsterInfoList.getMonsterIndexes().map {
        dndApi.getMonsterAt(it).await()
    }
}