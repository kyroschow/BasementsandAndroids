package com.example.chichow25.basementsandandroids.libgdx.core

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.app.KtxGame
import ktx.assets.toInternalFile
import ktx.async.enableKtxCoroutines
import ktx.inject.Context
import ktx.style.*

/**
 * Created by testaccount on 5/7/18.
 */
class Application : KtxGame<Screen>() {
    val context = Context()

    override fun create() {
        enableKtxCoroutines(asynchronousExecutorConcurrencyLevel = 1)
        context.register {
            bindSingleton(TextureAtlas("skin.atlas"))
            bindSingleton<Batch>(SpriteBatch())
            bindSingleton<Viewport>(ScreenViewport())
            bindSingleton(Stage(inject(), inject()))
            bindSingleton(createSkin(inject()))
        }
    }

    fun createSkin(atlas: TextureAtlas): Skin = skin(atlas) { skin ->
        add(defaultStyle, BitmapFont())
        add("mordred_bold", FreeTypeFontGenerator("mordred_bold.ttf".toInternalFile())
                .generateFont(FreeTypeFontGenerator.FreeTypeFontParameter().apply {
                    borderWidth = 2f
                    borderColor = Color.GRAY
                    size = 50
                }))
        label {
            font = skin[defaultStyle]
        }
        label("mordred_bold") {
            font = skin["mordred_bold"]
        }
        textButton("mordred_bold") {
            font = skin["mordred_bold"]
            overFontColor = Color.GRAY
            downFontColor = Color.DARK_GRAY
        }
        window {
            titleFont = skin[defaultStyle]
            stageBackground = skin["black-alpha"]
        }
    }

    override fun dispose() {
        context.dispose()
    }
}