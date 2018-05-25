package com.bna.game.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.bna.game.BnaGame
import ktx.actors.onClick
import ktx.app.KtxScreen
import ktx.scene2d.label
import ktx.scene2d.table

class Menu(val stage : Stage, val app : BnaGame) : KtxScreen {

    //val backgroundImage = Texture("602373-2880x1800")
    val view = table {
        setFillParent(true)

        //background = TextureRegionDrawable(TextureRegion(backgroundImage,0,0,600,600))
        touchable = Touchable.enabled

        label(text = "Play", style = "barbarian").cell(padLeft = 320f, row = true, padBottom = 5f)
                .onClick { app.setScreen<GameUI>() }
        //label(text = "point to aim", style = "decorative").cell(padLeft = 335f, row = true, padBottom = 10f)
        //label(text = "QWER to cast", style = "decorative").cell(padLeft = 290f, row = true, padBottom = 240f)
    }

    override fun show() {
        stage.addActor(view)
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        stage.act(delta)
        Gdx.input.inputProcessor = stage
    }

    override fun hide() {
        view.remove()
    }

}