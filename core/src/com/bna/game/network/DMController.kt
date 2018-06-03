package com.bna.game.network

import io.socket.client.Socket
import org.json.JSONObject

class DMController(private val socket: Socket, private val callback: (DMGameStateChange) -> Unit) {

    init {
        configSocketEvents()
    }

    private fun configSocketEvents(): Unit = with(socket) {
        on("DMGameStateUpdate") {
            val json = it[0] as JSONObject
            callback(DMGameStateUpdate(json.getJSONObject("gameState")))
        }
        on("PlayerTurnChange") {
            val json = it[0] as JSONObject
            callback(PlayerTurnChange(json.getJSONObject("gameState"), json.getInt("x"), json.getInt("y")))
        }
    }

    fun updateGameState(gameState: JSONObject) {
        socket.emit("updateGameStateAsDM", json("gameState" to gameState.toString()))
    }
}