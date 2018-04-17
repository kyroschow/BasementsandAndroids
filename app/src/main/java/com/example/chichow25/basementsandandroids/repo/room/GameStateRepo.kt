package com.example.chichow25.basementsandandroids.repo.room

import io.reactivex.Observable

/**
 * Created by per6 on 4/17/18.
 */
interface GameStateRepo {
    fun isGameStateRepoEmpty(): Observable<Boolean>

    fun insertGameState(vararg gameState: GameState): Observable<Boolean>

    fun loadGameStates(): Observable<List<GameState>>
}