package com.bna.game.notretrofit

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Net
import com.badlogic.gdx.net.HttpParametersUtils
import kotlinx.coroutines.experimental.suspendCancellableCoroutine

/**
 * Created by testaccount on 5/22/18.
 */
class RequestHelper(val url: String, val request: Net.HttpRequest){
    suspend fun get(parameters: HashMap<String, String>, header: HashMap<String, String>) = suspendCancellableCoroutine<String> { continuation ->
        request.url = url
        request.content = HttpParametersUtils.convertHttpParameters(parameters)
        request.setHeader(header.keys.elementAt(0), header.values.elementAt(0))
        Gdx.net.sendHttpRequest(request, object : Net.HttpResponseListener {
            override fun handleHttpResponse(httpResponse: Net.HttpResponse?) {
                httpResponse?.also {
                    continuation.resume(it.resultAsString)
                }
            }

            override fun cancelled() {
                continuation.cancel(Throwable("Request cancelled"))
            }

            override fun failed(t: Throwable?) {
                continuation.resumeWithException(Throwable("Callback failed with message ${t?.message}"))
            }

        })
    }
}