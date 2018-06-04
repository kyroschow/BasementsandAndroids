package com.bna.game.network

import org.json.JSONObject

sealed class DMGameStateChange

data class DMGameStateUpdate(val jsonObject: JSONObject): DMGameStateChange()
data class PlayerTurnChange(val jsonObject: JSONObject, val x: Int, val y: Int): DMGameStateChange()
data class PlayerConnectionChange(val connected: Boolean, val id: Int): DMGameStateChange()