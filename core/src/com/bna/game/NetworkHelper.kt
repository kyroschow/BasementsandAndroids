package com.bna.game

import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONException
import org.json.JSONObject

class NetworkHelper(val url: String, val port: Short, val isHost: Boolean) {

    private lateinit var id: String

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

    fun configSocketEvents(): Unit = with(socket) {
        on(Socket.EVENT_CONNECT) {
            gdxLog("Connected!")
        }
        on("SocketID") {
            val data = it[0] as JSONObject
            try {
                id = data.getString("id")
                gdxLog("My ID: $id")
            } catch (e: JSONException) {
                gdxLog(e.message, e)
            }
        }
        on("NewPlayerJoined") {
            val data = it[0] as JSONObject
            try {
                val id = data.getString("id")
                gdxLog("New PlayerModel Joined: $id")
            } catch (e: JSONException) {
                gdxLog(e.message, e)
            }
        }
    }
}