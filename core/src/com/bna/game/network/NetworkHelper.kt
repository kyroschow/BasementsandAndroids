package com.bna.game.network

import com.beust.klaxon.Klaxon
import com.bna.game.gdxLog
import com.bna.game.model.GameState
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONException
import org.json.JSONObject
import kotlin.properties.Delegates

open class NetworkHelper(var url: String, var port: Short) {

    private lateinit var id: String
    protected var socket: Socket by Delegates.observable(IO.socket("$url:$port")) { _, old, _ -> old.disconnect() }
    protected val klaxon = Klaxon()
    protected val parser = klaxon.parser()

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

    fun reconnect() {
        socket = IO.socket("$url:$port")
        connect()
    }

    fun disconnect() {
        try {
            socket.disconnect()
        } catch (e: Exception) {
            gdxLog(e.message, e)
        }
    }

    fun emitUpdatedGameState(gameState: GameState) {
        val jsonString = klaxon.toJsonString(gameState)
        gdxLog("JsonString: $jsonString")
        try {
            socket.emit("UpdateGameStateToServer", jsonString)
        } catch (e: Exception) {
            gdxLog("Cannot update GameState to server", e)
        }
    }

    open fun configSocketEvents(): Unit = with(socket) {
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