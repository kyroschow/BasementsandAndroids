/*
package com.bna.game.network

import com.bna.game.gdxLog
import com.bna.game.model.PlayerModel

class PlayerNetworkHelper(url: String, port: Short, onGameStateChange: (GameStateChange) -> Unit) : GameNetworkHelper(url, port, onGameStateChange) {
    lateinit var myModel: PlayerModel
    var isTurn = false

    companion object {
        const val SERVER_INIT_GAME_STATE = "ServerInitGameState"
        const val START_GAME_CHANGE = "StartGameChange"
        const val TURN_CHANGE = "TurnChange"
    }

    override fun configSocketEvents() {
        super.configSocketEvents()
        socket.listenFor(SERVER_INIT_GAME_STATE, START_GAME_CHANGE, TURN_CHANGE)
    }

    override fun handleEvent(eventType: String, data: List<Any>) {
        super.handleEvent(eventType, data)
        when(eventType) {
            SERVER_INIT_GAME_STATE -> {
                gameState = data[0].toString().toGameState()
                gameState.players.forEach { if(it.id == id) myModel = it }
            }
            START_GAME_CHANGE -> onGameStateChange(StartGameChange(gameState))
            TURN_CHANGE -> {
                isTurn = (data[0].toString().toJsonObject()).boolean("isTurn")!!
                onGameStateChange(TurnChange(isTurn))
            }
        }
    }

    fun emitUpdatePlayerState() {
        gdxLog(myModel.toString())
        if (!isTurn) throw IllegalStateException("Not this player's turn!")
        try {
            socket.emit("UpdatePlayerStateToServer", myModel)
        } catch (e: Exception) {
            gdxLog("Cannot update player state to server", e)
        }
    }

    */
/**
     * Apply changes to this player and emit to server
     * Quick way of updating player position or status, without parsing the whole GameState
     * Used for movement (up, down left right displays quickly on everyone's screen)
     *//*

    inline fun emitPlayerChange(change: PlayerModel.() -> Unit) {
        myModel.apply(change)
        emitUpdatePlayerState()
    }
}*/
