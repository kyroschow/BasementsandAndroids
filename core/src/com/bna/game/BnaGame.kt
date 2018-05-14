package com.bna.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.socket.client.IO
import io.socket.client.Socket

class BnaGame : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var img: Texture? = null

    //multiplayer
    private lateinit var socket: Socket

    override fun create() {
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
