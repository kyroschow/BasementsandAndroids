package com.bna.game.model

import kotlin.reflect.KProperty

class GameState(players: Array<PlayerModel>, enemies: Array<EnemyModel>) : PropertyAwareObject(), PropertyAwareListener {
    //empty constructor for db
    constructor() : this(emptyArray(), emptyArray())

    var players by observableProperty(players)
    var enemies by observableProperty(enemies)

    override fun onPropertyChange(vararg infos: PropertyChangeInfo) = infos.forEach { info ->
        when (info.parent) {
            is PlayerModel -> listeners.forEach { it.onPropertyChange(info) }
            is EnemyModel -> listeners.forEach { it.onPropertyChange(info) }
        }
    }

    override fun changeHandler(prop: KProperty<*>, old: Any, new: Any) {
        when (prop.name) {
            "players" -> players.forEach { it.addPropertyChangeListener(this) }
            "enemies" -> enemies.forEach { it.addPropertyChangeListener(this) }
        }
        super.changeHandler(prop, old, new)
    }
}