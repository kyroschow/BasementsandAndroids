package com.bna.game.logic

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.box2d.createWorld
import ktx.math.vec3
import ktx.scene2d.Scene2DSkin

/**
 * Created by per6 on 5/17/18.
 */
class GameManager(val batch: Batch, val background: TextureRegion, skin: Skin = Scene2DSkin.defaultSkin){
    val camera = OrthographicCamera(32f, 32f)
    val world = createWorld()
    val temp = vec3()
}