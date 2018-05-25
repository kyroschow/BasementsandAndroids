package com.bna.game.model

import kotlin.reflect.KProperty

class GameState(players: Array<PlayerModel>, enemies: Array<EnemyModel>) : PropertyAwareObject(), PropertyAwareListener {
    //empty constructor for db
    constructor() : this(emptyArray(), emptyArray())

    var players by observableProperty(players, ::playersHandler)
    var enemies by observableProperty(enemies)

    override fun onPropertyChange(propertyAwareObject: PropertyAwareObject) {
        when (propertyAwareObject) {
            is PlayerModel -> listeners.forEach { it.onPropertyChange(this) }
            is EnemyModel -> listeners.forEach { it.onPropertyChange(this) }
        }
    }

    private fun playersHandler(prop: KProperty<*>, old: Array<PlayerModel>, new: Array<PlayerModel>) {
        changeHandler(prop, old, new)
        new.forEach {
            if (old.isEmpty()) it.addPropertyChangeListener(this)
            else it.listeners.addAll(old[0].listeners)
        }
        old.forEach { it.listeners.clear() }
    }
}