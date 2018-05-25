package com.bna.game.network

import com.beust.klaxon.JsonObject
import com.bna.game.gdxLog
import com.bna.game.model.GameState
import com.bna.game.model.PropertyAwareListener
import com.bna.game.model.PropertyAwareObject
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class PlayerNetworkHelper(url: String, port: Short) : NetworkHelper(url, port), PropertyAwareListener {

    var gameState: GameState by Delegates.observable(GameState(), ::gameStateChangeHandler)
        private set
    var isTurn: Boolean = false
        private set
    var onGameStateChange: (GameStateChange) -> Unit = { throw IllegalStateException("No listener attached") }

    override fun configSocketEvents() {
        super.configSocketEvents()
        with(socket) {
            on("InitGameStateFromServer") { //from null to actual object emitted from server
                gameState = klaxon.parse<GameState>(it[0].toString())!!
            }
            on("StartGameChange") {
                onGameStateChange(StartGameChange(gameState))
            }
            on("TurnChange") {
                val json = parser.parse(it[0].toString()) as JsonObject
                isTurn = json.boolean("isTurn")!!
                onGameStateChange(TurnChange(isTurn))
            }
            on("UpdateGameStateChange") {
                gameState = klaxon.parse<GameState>(it[0].toString())!!
                onGameStateChange(UpdateGameStateChange(gameState))
            }
        }
    }

    override fun onPropertyChange(propertyAwareObject: PropertyAwareObject) {
        if (isTurn) emitUpdatedGameState(gameState)
        else throw IllegalStateException("Cannot emit to server, invalid turn")
    }

    private fun gameStateChangeHandler(prop: KProperty<*>, old: GameState, new: GameState) {
        new.listeners.addAll(old.listeners)
        old.listeners.clear()
    }
}

sealed class GameStateChange
data class StartGameChange(val gameState: GameState): GameStateChange()
data class TurnChange(val turn: Boolean): GameStateChange()
data class UpdateGameStateChange(val gameState: GameState): GameStateChange()