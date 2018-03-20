package com.example.chichow25.basementsandandroids

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by chichow25 on 3/19/18.
 */
suspend fun <T> Call<T>.await() = suspendCoroutine<T> { continuation ->
    enqueue(object : Callback<T> {

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            continuation.resumeWithException(Throwable("Retrofit Callback Failed", t))
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if (response?.body() == null) {
                continuation.resumeWithException(IllegalStateException("Response body is null"))
            } else {
                response.body()?.let {
                    continuation.resume(it)
                }
            }
        }

    })
}