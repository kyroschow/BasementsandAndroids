package com.bna.game.view.logic

import box2dLight.PointLight
import box2dLight.RayHandler
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.app.color
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
    val lightSystem = RayHandler(world).apply {
        setAmbientLight(0f, 0f, 0f, 0.6f)
        setBlur(true)
    }
    val light = PointLight(lightSystem, 100, color(1f, 1f, 1f, 0.6f), 20f, 0f, 0f).apply {
        isSoft = false
    }

    fun update(delta: Float) {
        world.step(delta, 8, 3)
    }
}