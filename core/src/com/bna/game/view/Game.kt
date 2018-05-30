package com.bna.game.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.bna.game.view.actor.TokenActor
import ktx.app.KtxGame

/**
 * Created by testaccount on 5/28/18.
 */
const val TAG = "Game"
class Game : KtxGame<Screen>() {

    //squares.foreach { dnd.addTarget(it) }
    private val stage: Stage by lazy {
        Stage(StretchViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))
    }
    override fun create() {
        Gdx.input.inputProcessor = stage
        val tokenActor = TokenActor()
        stage.addActor(tokenActor)
        val navDrawer = NavigationDrawer(400F, 1920F)
        val textureAtlas = TextureAtlas("unnamed.atlas")
        val archer = Image(textureAtlas.findRegion("archer"))
        val barbarian = Image(textureAtlas.findRegion("barbarian"))
        val bard = Image(textureAtlas.findRegion("bard"))
        val enemy = Image(textureAtlas.findRegion("enemy"))
        val fighter = Image(textureAtlas.findRegion("fighter"))
        val mage = Image(textureAtlas.findRegion("mage"))
        val rogue = Image(textureAtlas.findRegion("rogue"))
        val tree = Image(textureAtlas.findRegion("tree"))
        val backgroundImage = Image(Sprite(textureAtlas.findRegion("602373-2880x1800")))

        navDrawer.apply {
            add(archer).pad(35f, 52f, 35f, 52f)
            add(barbarian).pad(35f, 52f, 35f, 52f)
            add(bard).pad(35f, 52f, 35f, 52f)
            add(enemy).pad(35f, 52f, 35f, 52f)
            add(fighter).pad(35f, 52f, 35f, 52f)
            add(mage).pad(35f, 52f, 35f, 52f)
            add(rogue).pad(35f, 52f, 35f, 52f)
            add(tree).pad(35f, 52f, 35f, 52f)
            background = backgroundImage.drawable
            bottom().left()
            setWidthBackDrag(0f)
            setWidthStartDrag(40f)
            touchable = Touchable.enabled
        }
        backgroundImage.setFillParent(true)
        stage.addActor(backgroundImage)
        navDrawer.setFadeBackground(backgroundImage, 0.5f)

        stage.addActor(navDrawer)

        archer.name = "ARCHER"
        barbarian.name = "BARBARIAN"
        bard.name = "BARD"
        enemy.name = "ENEMY"
        fighter.name = "FIGHTER"
        mage.name = "MAGE"
        rogue.name = "ROGUE"
        tree.name = "TREE"
        //TODO: Drag and drop from slide out menu to stage
        val clickListener = object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                val closed = navDrawer.isCompletelyClosed
                val actor = event?.target

                when(actor?.name) {
                    "ARCHER" -> Gdx.app.debug(TAG, "Archer clicked")
                    "BARBARIAN" -> Gdx.app.debug(TAG, "Barbarian clicked")
                    "BARD" -> Gdx.app.debug(TAG, "Bard clicked")
                    "ENEMY" -> Gdx.app.debug(TAG, "Enemy clicked")
                    "FIGHTER" -> Gdx.app.debug(TAG, "Fighter clicked")
                    "MAGE" -> Gdx.app.debug(TAG, "Mage clicked")
                    "ROGUE" -> Gdx.app.debug(TAG, "Rogue clicked")
                    "TREE" -> Gdx.app.debug(TAG, "Tree clicked")
                    else -> {
                        Gdx.app.debug(TAG, "Something that definitely shouldn't be in this menu clicked")
                    }
                }
            }
        }

        val skin = Skin()
        skin.add("default", Label.LabelStyle(BitmapFont(), Color.WHITE))
        skin.add("badlogic", Texture("badlogic.jpg"))
        val table = Table(skin)
        val tableSection = VerticalGroup()
        val gridSection = VerticalGroup()
        val hContainer = HorizontalGroup()
        val squareWidth: Float = tableSection.width / 20
        val squareHeight: Float = tableSection.height / 20
        val squares = mutableListOf<Image>()
        val square = Image(skin, "badlogic")
        for (y in 0..20) {
            for (x in 0..20) {
                square.setBounds(x * squareWidth, y * squareWidth, squareWidth, squareHeight)
                squares.add(square)
            }
        }
        squares.forEach { table.add(it) }


        val dnd = DragAndDrop()
        squares.forEach {
            dnd.addSource(object : DragAndDrop.Source(it) {
                val payload = DragAndDrop.Payload()
                override fun dragStart(event: InputEvent?, x: Float, y: Float, pointer: Int): DragAndDrop.Payload {

                    return payload
                }
            })
        }

    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        stage.act(Gdx.graphics.deltaTime)
        stage.draw()
    }

    override fun dispose() {
        stage.dispose()
    }
}