package com.bna.game.view

import com.badlogic.gdx.Screen
import ktx.app.KtxGame

/**
 * Created by testaccount on 5/28/18.
 */
class Application() : KtxGame<Screen>() {
    override fun create() {
        setScreen<Game>()
    }

    override fun dispose() {
        getScreen<Game>().dispose()
    }
}