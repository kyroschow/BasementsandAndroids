package com.bna.game.model

class EnemyModel(x: Int, y: Int, health: Int, initiative: Int): PropertyAwareObject() {

    var x by observableProperty(x)
    var y by observableProperty(y)
    var health by observableProperty(health)
    var initiative by observableProperty(initiative)
}