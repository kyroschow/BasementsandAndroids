package com.example.chichow25.basementsandandroids.libgdx.core.view.logic

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.math.vec3
import ktx.scene2d.Scene2DSkin

/**
 * Created by testaccount on 5/7/18.
 */
class GameManager(val batch: Batch, val background: TextureRegion, skin: Skin = Scene2DSkin.defaultSkin){
    val width = Gdx.graphics.width
    val height = Gdx.graphics.height
    val camera = OrthographicCamera(32f * (height / width), 32f)
    val temp = vec3()
}