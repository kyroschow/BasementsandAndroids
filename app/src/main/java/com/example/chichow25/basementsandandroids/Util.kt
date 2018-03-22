package com.example.chichow25.basementsandandroids

import android.content.Context
import android.content.res.Resources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.nio.charset.Charset
import kotlin.coroutines.experimental.suspendCoroutine
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter
import java.util.*
import io.realm.SyncUser.fromJson
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by chichow25 on 3/19/18.
 */
suspend fun <T> Call<T>.await() = suspendCoroutine<T> { continuation ->

    enqueue(object : Callback<T> {

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            continuation.resumeWithException(Throwable("Retrofit Callback Failed", t))
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

inline fun <reified T> loadJsonFromAssets(context: Context, filename: String): T {

    val string = context.assets.open(filename).bufferedReader().use {
        it.readText()
    }
    return Gson().fromJson(string, T::class.java)
}