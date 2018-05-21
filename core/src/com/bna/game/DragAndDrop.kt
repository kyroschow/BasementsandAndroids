package com.bna.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin


//import com.badlogic.gdx.tests.utils.GdxTest;

class DragAndDrop /*: GdxTest*/{

    var stage: Stage? = null

    fun create(){
        stage = Stage()
        Gdx.input.setInputProcessor(stage)

        val skin : Skin = Skin()
        //skin.add("", )

    }
}
