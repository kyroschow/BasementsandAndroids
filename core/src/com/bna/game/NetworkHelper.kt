package com.bna.game

import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject

class NetworkHelper(val url: String, val port: Short) {

    private val socket: Socket by lazy {
        IO.socket("$url:$port")
    }

    init {
        require(port in 2000..9999) { "invalid port" }
    }

    fun connect() {
        try {
            socket.connect()
        } catch (e: Exception) {
            gdxLog(e.message, e)
        }
    }

    fun disconnect() {
        try {

            socket.disconnect()
        } catch (e: Exception) {
            gdxLog(e.message, e)
        }
    }

    fun configSocketEvents() {
        socket.apply {
            on(Socket.EVENT_CONNECT) {
                gdxLog("Connected!")
            }
            on("SocketID") {
                val data = it[0] as JSONObject
                try {
                    val id = data.getString("id")
                    gdxLog("My ID: $id")
                } catch (e: JSONException) {
                    gdxLog(e.message, e)
                }
            }
            on("NewPlayer") {
                val data = it[0] as JSONObject
                try {
                    val id = data.getString("id")
                    gdxLog("New Player Connected: $id")
                } catch (e: JSONException) {
                    gdxLog(e.message, e)
                }
            }
        }
    }

    inline fun Socket.on(event: String, crossinline listener: Socket.(args: Array<Any?>) -> Unit) {
        on(event, object : Emitter.Listener {
            override fun call(vararg args: Any?) {
                listener(arrayOf(args))
            }
        })
    }
}