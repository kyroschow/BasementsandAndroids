package com.bna.game.network

import com.bna.game.network.dm.DMController
import com.bna.game.network.dm.DMGameStateChange
import com.bna.game.network.player.PlayerController
import com.bna.game.network.player.PlayerGameStateChange
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withTimeout
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.coroutines.experimental.suspendCoroutine
import kotlin.properties.Delegates

class NetworkHelper(url: String) {
    private val urlWithPort: String get() = "$url:8080"
    private var socket: Socket = IO.socket(urlWithPort)
    var url: String by Delegates.observable(url) { _, _, _ ->
        socket.disconnect()
        socket = IO.socket(urlWithPort)
        socket.disconnect()
    }

    fun connect() {
        socket.disconnect()
        socket.connect()
    }

    fun reconnect() = connect()

    fun disconnect() = socket.disconnect()

    suspend fun joinAsPlayer(roomName: String, callback: (PlayerGameStateChange) -> Unit): PlayerController = suspendCoroutine { continuation ->
        launch(continuation.context) {
            withTimeout(15, TimeUnit.SECONDS) { continuation.resumeWithException(TimeoutException("Server took to long to respond")) }
        }
        socket.emit("JoinAsPlayer", json("roomName" to roomName))
        socket.once("JoinedAsPlayer") {
            continuation.resume(PlayerController(socket, callback))
        }
    }

    suspend fun joinAsDM(gameStateJSON: String, callback: (DMGameStateChange) -> Unit): DMController = suspendCoroutine { continuation ->
        launch(continuation.context) {
            withTimeout(15, TimeUnit.SECONDS) { continuation.resumeWithException(TimeoutException("Server took to long to respond")) }
        }
        socket.emit("JoinAsDM", json("gameState" to gameStateJSON))
        socket.once("JoinedAsDM") {
            continuation.resume(DMController(socket, callback))
        }
    }
}