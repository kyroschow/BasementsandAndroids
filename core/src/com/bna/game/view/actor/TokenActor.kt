package com.bna.game.view.actor

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable

/**
 * Created by testaccount on 5/28/18.
 */
class TokenActor: Actor() {
    //TODO: Replace generic placeholder for sprite with atlas access
    val sprite = Sprite(Texture(Gdx.files.internal("badlogic.jpg")))
    init{
        setBounds(sprite.x, sprite.y, sprite.width, sprite.height)
        touchable = Touchable.enabled
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        sprite.draw(batch)
    }

    override fun positionChanged() {
        sprite.setPosition(x, y)
    }
}