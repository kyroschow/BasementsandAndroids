package com.example.chichow25.basementsandandroids.libgdx.core.view

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.example.chichow25.basementsandandroids.libgdx.core.view.actor.TokenIcon
import ktx.app.KtxScreen
import ktx.collections.gdxArrayOf

/**
 * Created by testaccount on 5/7/18.
 */
class Game(val stage: Stage, val batch: Batch) : KtxScreen {
    val tokens = gdxArrayOf<TokenIcon>()
}