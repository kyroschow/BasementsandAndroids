package com.bna.game.view

import com.badlogic.gdx.Screen
import ktx.app.KtxGame

class Game3 : KtxGame<Screen>() {
    override fun create() {
        addScreen(Map())
        setScreen<Map>()
    }
}