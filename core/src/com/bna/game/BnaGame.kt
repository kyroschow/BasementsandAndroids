package com.bna.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.socket.client.IO
import io.socket.client.Socket
import ktx.app.KtxGame
import ktx.async.enableKtxCoroutines
import ktx.inject.Context

class BnaGame : KtxGame<Screen>() {
    private var batch: SpriteBatch? = null
    private var img: Texture? = null

    //multiplayer
    private lateinit var socket: Socket
    private val context = Context()

    override fun create() {
        enableKtxCoroutines(asynchronousExecutorConcurrencyLevel = 1)
        context.register {

        }
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
        //multiplayer
        gdxLog("create()")
        try {
            socket = IO.socket("http://10.100.139.103:8080")
            socket.connect()
        } catch (e: Exception) {
            gdxLog(e.message, e, "BnaGame")
        }
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.apply {
            begin()
            draw(img, 0f, 0f)
            end()
        }
    }

    override fun dispose() {
        batch?.dispose()
        img?.dispose()
    }
}
