package com.bna.game.network

import org.json.JSONObject

sealed class PlayerGameStateChange

data class PlayerGameStateUpdate(val gameState: JSONObject): PlayerGameStateChange()
data class TurnChangeSelf(val gameState: JSONObject): PlayerGameStateChange()
data class TurnChangeOther(val gameState: JSONObject, val x: Int, val y: Int): PlayerGameStateChange()