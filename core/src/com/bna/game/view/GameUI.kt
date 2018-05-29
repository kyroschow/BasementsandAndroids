package com.bna.game.view

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.ui.List
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop
import ktx.app.KtxScreen

class GameUI(val skin: Skin,val stage : Stage, val batch: Batch) : KtxScreen {

    override fun show(){
        skin.add("badlogic", Texture("badlogic.jpg"))
        val table = Table(skin)
        val tableSection = VerticalGroup()
        val gridSection = VerticalGroup()
        val hContainer = HorizontalGroup()
        val iconList = getIcons()

        val squareWidth : Float = tableSection.width / 20
        val squareHeight : Float = tableSection.height / 20
//        batch.begin()
        for (y in 0 until 20) {
            for (x in 0 until 20) {
//                square.x = x * squareWidth
//                square.y = y * squareHeight
//                square.draw(batch)
                val square = Image(skin, "badlogic")
                square.setBounds(x * squareWidth, y * squareWidth, squareWidth, squareHeight)
                stage.addActor(square)
            }
        }
//        batch.end()


        table.setFillParent(true)
        table.defaults()
        table.add("Icons").row()
        table.add(iconList).expand().fill()

        val dragdrop = DragAndDrop()
        dragdrop.addSource(object : DragAndDrop.Source(iconList) {
            val payload = DragAndDrop.Payload()
            override fun dragStart(event: InputEvent, x: Float, y: Float, pointer: Int): DragAndDrop.Payload {
                val item = iconList.selected
                payload.`object` = item
                iconList.items.removeIndex(iconList.selectedIndex)
                payload.dragActor = iconList.selected
                /*payload.invalidDragActor =
                payload.validDragActor = */ //TODO: DO AFTER GRID IS MADE
                return payload
            }

            override fun dragStop(event: InputEvent, x: Float, y: Float, pointer: Int, payload: DragAndDrop.Payload, target: DragAndDrop.Target) {
                if (target == null)
                    iconList.getItems().add(payload.`object` as Image)
            }
        })
        /*dragdrop.addTarget(object : Target(sell) {
            fun drag(source: DragAndDrop.Source, payload: Payload, x: Float, y: Float, pointer: Int): Boolean {
                return "Cucumber" != payload.`object`
            }

            fun drop(source: DragAndDrop.Source, payload: Payload, x: Float, y: Float, pointer: Int) {
                sell.getItems().add(payload.`object` as String)
            }
        })*/ //TODO: Later



        hContainer.children.add(tableSection,gridSection)
        stage.addActor(hContainer)
    }

    private fun getIcons(): List<Image> {
        //TODO: Find some way to get images and return as a list
        val list : com.badlogic.gdx.scenes.scene2d.ui.List<Image> = List<Image>(skin)
        return list
    }

}