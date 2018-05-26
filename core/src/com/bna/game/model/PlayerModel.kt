package com.bna.game.model

data class PlayerModel(var id: String, //DO NOT save into database
        var x: Int, var y: Int, var health: Int, var initiative: Int) {
    constructor(): this("", 0, 0, 0, 0)
}