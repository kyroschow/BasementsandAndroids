package com.bna.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import io.socket.client.Socket
import ktx.app.KtxGame
import ktx.assets.toInternalFile
import ktx.async.enableKtxCoroutines
import ktx.inject.Context
import ktx.scene2d.Scene2DSkin
import ktx.style.*
/*import com.ownedoutcomes.view.Game
import com.ownedoutcomes.view.Menu*/ //TODO: make this work

class BnaGame : KtxGame<Screen>() {
    private var batch: SpriteBatch? = null
    private var img: Texture? = null

    //multiplayer
    private lateinit var socket: Socket
    private val context = Context()

    override fun create() {
        enableKtxCoroutines(asynchronousExecutorConcurrencyLevel = 1)
        context.register {
            bindSingleton(TextureAtlas("skin.atlas"))
            bindSingleton<Batch>(SpriteBatch())
            bindSingleton<Viewport>(ScreenViewport())
            bindSingleton(Stage(inject(), inject()))
            bindSingleton(createSkin(inject()))
            Scene2DSkin.defaultSkin = inject()
            bindSingleton(this@BnaGame)
            /*bindSingleton(Menu(inject(), inject()))   //TODO: incorrect imports
            bindSingleton(Game(inject(), inject()))*/
        }
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    fun createSkin(atlas: TextureAtlas): Skin = skin(atlas) { skin ->
        add(defaultStyle, BitmapFont())
        add("barbarian", FreeTypeFontGenerator("barbarian.ttf".toInternalFile())
                .generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply {
                    /*borderWidth = 2f
                    borderColor = Color.GRAY
                    size = 50*/ //TODO:we can decide this later
                }))
        label {
            font = skin[defaultStyle]
        }
        label("barbarian") {
            font = skin["barbarian"]
        }
        textButton("barbarian") {
            /*font = skin["barbarian"]
            overFontColor = Color.GRAY
            downFontColor = Color.DARK_GRAY*/ //TODO:decide later
        }
        window {
            /*titleFont = skin[defaultStyle]
            stageBackground = skin["black-alpha"]*/ //TODO:decide later
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
