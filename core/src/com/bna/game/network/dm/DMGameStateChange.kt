package com.bna.game.network.dm

import org.json.JSONObject

sealed class DMGameStateChange

data class DMGameStateUpdate(val gameState: JSONObject): DMGameStateChange()
data class PlayerTurnChange(val gameState: JSONObject, val x: Int, val y: Int): DMGameStateChange()
data class PlayerConnectionChange(val connected: Boolean, val id: Int): DMGameStateChange()
data class EnemyTurnChange(val gameState: JSONObject): DMGameStateChange()
data class DMGameEnd(val gameState: JSONObject): DMGameStateChange()