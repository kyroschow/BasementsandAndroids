package com.bna.game.network.player

import com.bna.game.gdxLog
import com.bna.game.network.json
import io.socket.client.Socket
import org.json.JSONObject

class PlayerController(private val socket: Socket, private val callback: (PlayerGameStateChange) -> Unit) {
    init {
        configSocketEvents()
    }

    var isTurn: Boolean = false
        private set

    private fun configSocketEvents(): Unit = with(socket) {
        on(Socket.EVENT_CONNECT) {
            gdxLog("Connected")
        }
        on("PlayerConnectedChange") {
            val json = it[0] as JSONObject
            gdxLog("Player Connected, ID: ${json.getInt("id")}")
            callback(OtherPlayerConnectionChange(true, json.getInt("id")))
        }
        on("PlayerDisconnectedChange") {
            val json = it[0] as JSONObject
            gdxLog("Player Disconnected, ID: ${json.getInt("id")}")
            callback(OtherPlayerConnectionChange(false, json.getInt("id")))
        }
        on("SocketID") {
            val json = it[0] as JSONObject
            gdxLog("My ID: ${json.getInt("id")}")
        }
        //custom events
        on("PlayerGameStateUpdate") {
            val json = it[0] as JSONObject
            callback(PlayerGameStateUpdate(json.getJSONObject("gameState")))
        }
        on("TurnChangeSelf") {
            isTurn = true
            val json = it[0] as JSONObject
            callback(TurnChangeSelf(json.getJSONObject("gameState")))
        }
        on("TurnChangeOther") {
            isTurn = false
            val json = it[0] as JSONObject
            callback(TurnChangeOther(json.getJSONObject("gameState"), json.getInt("x"), json.getInt("y")))
        }
        on("StartGame") {
            val json = it[0] as JSONObject
            callback(StartGame(json.getJSONObject("gameState")))
        }
        on("GameEnd") {
            val json = it[0] as JSONObject
            callback(PlayerGameEnd(json.getJSONObject("gameState")))
        }
    }

    fun updateGameState(gameState: JSONObject) {
        require(isTurn) { "Incorrect Turn" }
        socket.emit("UpdateGameState", json("gameState" to gameState.toString()))
    }

    fun updatePosition(x: Int, y: Int) {
        require(isTurn) { "Incorrect Turn" }
        socket.emit("UpdateSelfPosition", json("x" to x, "y" to y))
    }
}