package com.example.chichow25.basementsandandroids.repo.util

import android.content.Context
import com.example.chichow25.basementsandandroids.repo.gamedata.EquipmentCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.experimental.suspendCoroutine
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by chichow25 on 3/19/18.
 */
suspend fun <T> Call<T>.await() = suspendCoroutine<T> { continuation ->

    enqueue(object : Callback<T> {

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            continuation.resumeWithException(Throwable("Retrofit Callback Failed: ${t?.message}", t))
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if (response?.body() != null) {
                response.body()?.let {
                    continuation.resume(it)
                }
            } else {
                continuation.resumeWithException(IllegalStateException("Response body is null"))
            }
        }

    })
}

fun Context.loadEquipmentCategoriesFromAssets(): List<EquipmentCategory> {
    val string = assets.open("equipment_list.json").bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<EquipmentCategory>>() {}.type
    return Gson().fromJson(string, type)
}

fun EquipmentCategory.getEquipmentIndexes()
        = equipment.map { it.url.substringAfterLast('/').toInt() }