package com.bna.game.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack
import com.badlogic.gdx.utils.Align


/**
 * Created by testaccount on 5/28/18.
 */
class NavigationDrawer(// only visual window and using scissor to avoid GPU to draw out of left-edge screen.
        private var areaWidth: Float, private var areaHeight: Float) : Table() {
    private val areaBounds = Rectangle()
    private val scissorBounds = Rectangle()

    // it's revealed with (widthStart = 60F;) when the user swipes a finger from the left edge of the screen with start touch.
    private var widthStart = 60f
    // when the user swipes a finger from the right edge of the screen, it goes into off-screen after (widthBack = 20F;).
    private var widthBack = 20f
    // speed of dragging
    var speed = 15f

    // some attributes to make real dragging
    private val clamp = Vector2()
    private val posTap = Vector2()
    private val end = Vector2()
    private val first = Vector2()
    private val last = Vector2()

    private var show = false
    private var isTouched = false
    private var isStart = false
    private var isBack = false
    private var auto = false
    private var enableDrag = true

    private var listener: NavigationDrawerListener? = null

    private var isMax = false
    private var isMin = false

    val isCompletelyClosed: Boolean
        get() = clamp.x == 0f

    val isCompletelyOpened: Boolean
        get() = clamp.x == this.width

    /**
     * Optional
     */
    private var menuButton = Actor()
    private var isRotateMenuButton = false
    private var menuButtonRotation = 0f

    /**
     * Optional
     */
    private var background = Actor()
    private var isFadeBackground = false
    private var maxFade = 1f

    fun setAreaWidth(areaWidth: Float) {
        this.areaWidth = areaWidth
    }

    fun setAreaHeight(areaHeight: Float) {
        this.areaHeight = areaHeight
    }

    init {

        this.setSize(areaWidth, areaHeight)
    }

    interface NavigationDrawerListener {
        fun moving(clamp: Vector2)
    }

    fun setNavigationDrawerListener(listener: NavigationDrawerListener) {
        this.listener = listener

    }

    fun setWidthStartDrag(widthStartDrag: Float) {
        this.widthStart = widthStartDrag
    }

    fun setWidthBackDrag(widthBackDrag: Float) {
        this.widthBack = widthBackDrag
    }

    fun showManually(show: Boolean, speed: Float) {
        this.auto = true
        this.show = show
        this.speed = speed
    }

    fun showManually(show: Boolean) {
        this.showManually(show, speed)
    }

    override fun draw(batch: Batch?, alpha: Float) {
        stage.calculateScissors(areaBounds.set(0f, 0f, areaWidth, areaHeight), scissorBounds)
        batch!!.flush()
        if (ScissorStack.pushScissors(scissorBounds)) {
            super.draw(batch, alpha)
            batch.flush()
            ScissorStack.popScissors()
        }

        if (isTouched() && inputX() < stgToScrX(this.width, 0f).x) {
            auto = false
            if (!isTouched) {
                isTouched = true
                first.set(scrToStgX(inputX(), 0f))
            }
            last.set(scrToStgX(inputX(), 0f)).sub(first)

            if (isCompletelyClosed)
            // open = false, close = true;
                startDrag()

            if ((isStart || isBack) && enableDrag)
            // open = false, close =
            // false;
                if (inputX() > stgToScrX(widthStart, 0f).x)
                    dragging()

            if (isCompletelyOpened)
            // open = true, close = false;
                backDrag()

        } else
            noDrag()

        updatePosition()

        moving()

        rotateMenuButton()

        fadeBackground()

    }

    private fun moving() {
        if (listener == null)
            return
        if (!isCompletelyClosed && !isCompletelyOpened) {
            listener!!.moving(clamp)
        } else {
            if (!isMax && isCompletelyOpened) {
                isMax = true
                isMin = false
                listener!!.moving(clamp)
            }
            if (!isMin && isCompletelyClosed) {
                isMin = true
                isMax = false
                listener!!.moving(clamp)
            }
        }

    }

    private fun updatePosition() {
        clamp.set(MathUtils.clamp(end.x, 0f, this.width), 0f)
        this.setPosition(clamp.x, 0f, Align.bottomRight)
    }

    private fun dragging() {
        if (isStart)
            end.set(scrToStgX(inputX(), 0f))

        if (isBack && last.x < -widthBack)
            end.set(last.add(this.width + widthBack, 0f))

    }

    private fun backDrag() {
        isStart = false
        isBack = true
        show = false
    }

    private fun startDrag() {
        // check if the player touch on the drawer to OPEN it.
        if (inputX() < stgToScrX(widthStart, 0f).x) {
            isStart = true
            isBack = false

            hintToOpen() // hint to player if he want to open the drawer
        }
    }

    private fun noDrag() {
        isStart = false
        isBack = false
        isTouched = false

        // set end of X to updated X from clamp
        end.set(clamp)

        if (auto) {
            if (show)
                end.add(speed, 0f) // player want to OPEN drawer
            else
                end.sub(speed, 0f) // player want to CLOSE drawer
        } else {
            if (toOpen())
                end.add(speed, 0f) // player want to OPEN drawer
            else if (toClose())
                end.sub(speed, 0f) // player want to CLOSE drawer
        }

    }

    private fun hintToOpen() {
        end.set(stgToScrX(widthStart, 0f))
    }

    private fun toOpen(): Boolean {
        return clamp.x > this.width / 2
    }

    private fun toClose(): Boolean {
        return clamp.x < this.width / 2
    }

    private fun stgToScrX(x: Float, y: Float): Vector2 {
        return stage.stageToScreenCoordinates(posTap.set(x, y))
    }

    private fun scrToStgX(x: Float, y: Float): Vector2 {
        return stage.screenToStageCoordinates(posTap.set(x, y))
    }

    private fun inputX(): Float {
        return Gdx.input.x.toFloat()
    }

    private fun isTouched(): Boolean {
        return Gdx.input.isTouched
    }

    private fun rotateMenuButton() {
        if (isRotateMenuButton)
            menuButton.rotation = clamp.x / this.width * menuButtonRotation
    }

    fun setRotateMenuButton(actor: Actor, rotation: Float) {
        this.menuButton = actor
        this.isRotateMenuButton = true
        this.menuButtonRotation = rotation
    }

    fun setEnableDrag(enableDrag: Boolean) {
        this.enableDrag = enableDrag
    }

    private fun fadeBackground() {
        if (isFadeBackground)
            background.setColor(background.color.r, background.color.g, background.color.b,
                    MathUtils.clamp(clamp.x / this.width / 2f, 0f, maxFade))
    }

    fun setFadeBackground(background: Actor, maxFade: Float) {
        this.background = background
        this.isFadeBackground = true
        this.maxFade = maxFade
    }

}