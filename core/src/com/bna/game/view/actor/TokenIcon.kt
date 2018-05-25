package com.bna.game.view.actor

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.bna.game.view.logic.TokenItem
import kotlinx.coroutines.experimental.Job
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.table

/**
 * Created by per6 on 5/17/18.
 */
class TokenIcon(val utility: Boolean, var currentToken: TokenItem){
    private lateinit var icon: Image
    private lateinit var label: Label
    val job: Job? = null
    val actor = table {
        icon = image(currentToken.name)
        label = label("", style = "decorative")
    }
}