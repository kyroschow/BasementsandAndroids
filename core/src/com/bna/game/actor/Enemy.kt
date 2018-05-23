package com.bna.game.actor

import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.physics.box2d.World
import com.bna.game.logic.GameManager


open class Enemy(
        world: World,
        var enemyX: Int,
        var enemyY: Int,
        val gameManager: GameManager,
        val _id: String,
        val index: Int,
        val name: String,
        val size: String,
        val type: String,
        val subtype: String,
        val alignment: String,
        val armor_class: Int,
        val hit_points: Int,
        val hit_dice: String,
        val speed: String,
        val strength: Int,
        val dexterity: Int,
        val constitution: Int,
        val intelligence: Int,
        val wisdom: Int,
        val constitution_save: Int,
        val intelligence_save: Int,
        val wisdom_save: Int,
        val history: Int,
        val perception: Int,
        val damage_vulnerabilities: String,
        val damage_resistances: String,
        val damage_immunities: String,
        val condition_immunities: String,
        val senses: String,
        val languages: String,
        val challenge_rating: Int,
        val url: String) : InputProcessor

{
    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyTyped(character: Char): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun scrolled(amount: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyUp(keycode: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keyDown(keycode: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override public fun touchDown(screenX : Int, screenY : Int): Boolean {
        if (screenX == enemyX && screenY == enemyY)
            return true
        return false
    }

    public fun inspect() {

    }

    public fun rollInitiative(): Int {
        return random.nextInt(1 - 20) + 20
    }

    public fun engage() {
        if (touchDown())
    }

}