package com.bna.game.view.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor

class TileActor(val sprite: Sprite) : Actor() {
    override fun draw(batch: Batch?, parentAlpha: Float) {
        sprite.draw(batch)
        super.draw(batch, parentAlpha)
    }
}