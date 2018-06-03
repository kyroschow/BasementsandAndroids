/*
package com.bna.game.network

import com.bna.game.model.GameState

class DMNetworkHelper(override var gameState: GameState, url: String, port: Short, onGameStateChange: (GameStateChange) -> Unit): GameNetworkHelper(url, port, onGameStateChange) {

    companion object {
        const val PLAYER_JOINED_CHANGE = "PlayerJoinedChange"
        const val PLAYER_DISCONNECTED_CHANGE = "PlayerDisconnectedChange"
        const val PLAYER_TURN_CHANGE = "PlayerTurnChange"
    }

    override fun configSocketEvents() {
        super.configSocketEvents()
        socket.listenFor(PLAYER_JOINED_CHANGE, PLAYER_DISCONNECTED_CHANGE, PLAYER_TURN_CHANGE)
    }

    override fun handleEvent(eventType: String, data: List<Any>) {
        super.handleEvent(eventType, data)
        when(eventType) {
            PLAYER_JOINED_CHANGE -> {
                val json = data[0].toString().toJsonObject()
                onGameStateChange(PlayerJoinedChange(json.string("id")!!))
            }
            PLAYER_DISCONNECTED_CHANGE -> {
                val json = data[0].toString().toJsonObject()
                onGameStateChange(PlayerDisconnectedChange(json.string("id")!!))
            }
            PLAYER_TURN_CHANGE -> {
                val playerModel = data[0].toString().toPlayerModel()
                onGameStateChange(PlayerTurnChange(playerModel))
            }
        }
    }
    
    fun connectAsDM() {
        socket.connect()
        socket.emit("ConnectAsDM")
    }
}
*/
