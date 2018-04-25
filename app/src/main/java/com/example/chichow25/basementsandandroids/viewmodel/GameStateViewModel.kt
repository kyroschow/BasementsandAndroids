package com.example.chichow25.basementsandandroids.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.database.Cursor
import com.example.chichow25.basementsandandroids.repo.room.GameDataBase
import com.example.chichow25.basementsandandroids.repo.room.GameDataDao
import com.example.chichow25.basementsandandroids.repo.room.GameState
import kotlinx.coroutines.experimental.launch

/**
 * Created by chichow25 on 3/19/18.
 */
class GameStateViewModel(app: Application) : AndroidViewModel(app) {

    //Room database
    private val gameDataDao: GameDataDao = GameDataBase.getInstance(app).gameDataDao()
    val gameStateLiveData: LiveData<List<GameState>> = gameDataDao.getAll()

    val cursorData: Cursor = gameDataDao.getCursor()

    fun saveGameStateToDataBase(vararg gameStates: GameState) = launch {
        gameDataDao.insert(*gameStates)
    }

    fun deleteGameStateFromDataBase(vararg gameStates: GameState) = gameDataDao.delete(*gameStates)

    fun deleteAllGameStates() = gameDataDao.deleteAll()

    fun updateGameState(gameState: GameState) = gameDataDao.update(gameState)
}