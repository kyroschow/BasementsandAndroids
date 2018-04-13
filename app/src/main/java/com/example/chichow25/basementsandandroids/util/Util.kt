package com.example.chichow25.basementsandandroids.util

import android.content.Context
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import com.example.chichow25.basementsandandroids.repo.gamedata.EquipmentCategory
import com.example.chichow25.basementsandandroids.repo.gamedata.MonsterInfo
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

fun Context.loadMonsterInfosFromAssets() : List<MonsterInfo> {
    val string = assets.open("monsters_list.json").bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<MonsterInfo>>() {}.type
    return Gson().fromJson(string, type)
}

fun EquipmentCategory.getEquipmentIndexes() = equipment.map { it.url.substringAfterLast('/').toInt() }

fun List<MonsterInfo>.getMonsterIndexes() = map { it.url.substringAfterLast('/').toInt() }

suspend fun WifiP2pManager.isDiscoverPeersSuccessful(channel: WifiP2pManager.Channel): Boolean = suspendCoroutine {
    discoverPeers(channel, object : WifiP2pManager.ActionListener {

        override fun onSuccess() {
            it.resume(true)
        }

        override fun onFailure(reason: Int) {
            Log.e("WifiP2pManager", "discover peers failed with reason code: $reason")
            it.resume(false)
        }

    })
}

suspend fun WifiP2pManager.isConnectSuccessful(channel: WifiP2pManager.Channel, config: WifiP2pConfig): Boolean = suspendCoroutine {
    connect(channel, config, object : WifiP2pManager.ActionListener {

        override fun onSuccess() {
            it.resume(true)
        }

        override fun onFailure(reason: Int) {
            Log.e("WifiP2pManager", "connect failed with reason code: $reason")
            it.resume(false)
        }

    })
}