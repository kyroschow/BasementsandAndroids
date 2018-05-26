package com.bna.game.network

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.bna.game.gdxLog
import com.bna.game.model.GameState
import com.bna.game.model.PlayerModel
import io.socket.client.IO
import io.socket.client.Socket

abstract class GameNetworkHelper(var url: String, var port: Short, var onGameStateChange: (GameStateChange) -> Unit) {

    protected lateinit var id: String
    protected var socket  = IO.socket("$url:$port")
    protected val klaxon = Klaxon()
    protected val parser = klaxon.parser()
    open lateinit var gameState: GameState
        protected set

    companion object {
        const val SOCKET_ID = "SocketID"
        const val NEW_PLAYER_JOINED = "NewPlayerJoined"
        const val UPDATE_GAME_STATE_CHANGE = "UpdateGameStateChange"
        const val UPDATE_PLAYER_CHANGE = "UpdatePlayerChange"
    }

    init {
        require(port in 2000..9999) { "invalid port" }
    }

    fun connect() {
        try { socket.connect() }
        catch (e: Exception) { gdxLog(e.message, e) }
    }

    fun reconnect() {
        disconnect()
        socket = IO.socket("$url:$port")
        connect()
    }

    fun disconnect() {
        try { socket.disconnect() }
        catch (e: Exception) { gdxLog(e.message, e) }
    }

    fun emitUpdatedGameState() {
        gdxLog(gameState.toString())
        try { socket.emit("UpdateGameStateToServer", gameState) }
        catch (e: Exception) { gdxLog("Cannot update GameState to server", e) }
    }

    open fun configSocketEvents() {
        socket.listenFor(Socket.EVENT_CONNECT, SOCKET_ID, NEW_PLAYER_JOINED, UPDATE_GAME_STATE_CHANGE, UPDATE_PLAYER_CHANGE)
    }

    protected open fun handleEvent(eventType: String, data: List<Any>): Unit = when (eventType) {
        Socket.EVENT_CONNECT -> gdxLog("Connected!")
        SOCKET_ID -> {
            val json = data[0].toString().toJsonObject()
            id = json.string("id")!!
            gdxLog("My ID: $id")
        }
        NEW_PLAYER_JOINED -> {
            val json = data[0].toString().toJsonObject()
            val id = json.string("id")
            gdxLog("New Player Joined: $id")
        }
        UPDATE_GAME_STATE_CHANGE -> {
            gameState = data[0].toString().toGameState()
            onGameStateChange(UpdateGameStateChange(gameState))
        }
        UPDATE_PLAYER_CHANGE -> {
            val playerModel = data[0].toString().toPlayerModel()
            onGameStateChange(UpdatePlayerChange(playerModel))
        }
        else -> gdxLog("Event not handled in GameNetworkHelper: $eventType")
    }

    protected fun String.toJsonObject() = parser.parse(this) as JsonObject

    protected fun String.toGameState() = klaxon.parse<GameState>(this)!!

    protected fun String.toPlayerModel() = klaxon.parse<PlayerModel>(this)!!

    /**
     * Apply changes to GameState and emits to server
     * For players, this will end their turn
     */
    inline fun applyEmit(change: GameState.() -> Unit) {
        gameState.apply(change)
        emitUpdatedGameState()
    }

    private fun listener(data: Array<Any>) {
        val eventTypeData = data[0].toString().toJsonObject()
        handleEvent(eventTypeData.string("eventType")!!, data.drop(1))
    }

    protected fun Socket.listenFor(vararg events: String) = events.forEach { on(it, ::listener) }
}