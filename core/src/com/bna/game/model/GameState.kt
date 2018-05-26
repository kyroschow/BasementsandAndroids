package com.bna.game.model

data class GameState(var players: MutableList<PlayerModel>, var enemies: MutableList<EnemyModel>) {
    constructor(): this(mutableListOf(), mutableListOf())
}