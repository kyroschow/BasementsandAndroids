package com.bna.game.model

import kotlin.properties.Delegates

data class GameState(private val _players: Array<PlayerModel>,
                     private val _enemies: Array<EnemyModel>) {
    //empty constructor for db
    constructor() : this(emptyArray(), emptyArray())

    var players by observableVar("players", _players)
    var enemies by observableVar("enemies", _enemies)

    private var callback: ((Pair<String, Any>) -> Unit)? = null

    fun observe(callback: (Pair<String, Any>) -> Unit) {
        this.callback = callback
    }

    private fun <T> observableVar(name: String, initialValue: T) = Delegates.observable(initialValue) {
        _, _, new ->
        //TODO: notify game state changed
        callback?.invoke(name to new as Any)
    }
}