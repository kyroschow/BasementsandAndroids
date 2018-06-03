/*
package com.bna.game.network

import com.bna.game.model.GameState
import com.bna.game.model.PlayerModel

sealed class GameStateChange

//everyone
data class UpdateGameStateChange(val gameState: GameState): GameStateChange()
data class UpdatePlayerChange(val playerData: PlayerModel): GameStateChange()

//players
data class StartGameChange(val gameState: GameState): GameStateChange()
data class TurnChange(val turn: Boolean): GameStateChange()

//dm
data class PlayerJoinedChange(val id: String): GameStateChange()
data class PlayerDisconnectedChange(val id: String): GameStateChange()
data class PlayerTurnChange(val playerModel: PlayerModel): GameStateChange()*/
