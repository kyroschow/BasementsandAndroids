package com.bna.game.model

data class EnemyModel(var x: Int, var y: Int, var health: Int, var initiative: Int) {
    constructor(): this(0, 0, 0, 0)
}