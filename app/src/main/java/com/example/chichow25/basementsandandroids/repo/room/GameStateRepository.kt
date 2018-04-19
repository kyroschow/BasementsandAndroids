package com.example.chichow25.basementsandandroids.repo.room

import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by per6 on 4/17/18.
 */
/*
class GameStateRepository @Inject internal constructor(private val gameDataDao: GameDataDao): GameStateRepo {
    override fun isGameStateRepoEmpty(): Observable<Boolean> = Observable.fromCallable({ gameDataDao.getAll().isEmpty() })

    override fun insertGameState(vararg gameState: GameState): Observable<Boolean> {
        gameDataDao.insert(*gameState)
        return Observable.just(true)
    }

    override fun loadGameStates(): Observable<List<GameState>> = Observable.fromCallable({ gameDataDao.getAll() })

}*/
