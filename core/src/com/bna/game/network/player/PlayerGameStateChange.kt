package com.bna.game.network.player

import org.json.JSONObject

sealed class PlayerGameStateChange

data class PlayerGameStateUpdate(val gameState: JSONObject): PlayerGameStateChange()
data class TurnChangeSelf(val gameState: JSONObject): PlayerGameStateChange()
data class TurnChangeOther(val gameState: JSONObject, val x: Int, val y: Int): PlayerGameStateChange()
data class OtherPlayerConnectionChange(val isTurn: Boolean, val id: Int): PlayerGameStateChange()
data class StartGame(val gameState: JSONObject): PlayerGameStateChange()
data class PlayerGameEnd(val gameState: JSONObject): PlayerGameStateChange()