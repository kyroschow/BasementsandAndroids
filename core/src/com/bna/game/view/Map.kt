package com.bna.game.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.viewport.StretchViewport
import ktx.app.KtxScreen

class Map : KtxScreen {
    private val stage by lazy {
        Stage(StretchViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))
    }
    override fun render(delta: Float) {
        val group = Group()
        //val atlas = TextureAtlas("bna.atlas")
//        val actor = TileActor(Sprite(Texture("badlogic.jpg")))
        val actor = Image(Texture("badlogic.jpg"))
        val table = Table()
        table.setFillParent(true)
        table.debug()
        table.top().left()
        table.addActor(actor)
        table.addActor(actor)
        stage.addActor(table)
        stage.isDebugAll = true
        stage.draw()
        super.render(delta)
    }
}