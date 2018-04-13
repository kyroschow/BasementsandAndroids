package com.example.chichow25.basementsandandroids.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.chichow25.basementsandandroids.repo.Room.GameDataBase
import com.example.chichow25.basementsandandroids.repo.Room.GameDataDao
import com.example.chichow25.basementsandandroids.repo.Room.GameState

/**
 * Created by chichow25 on 3/19/18.
 */
class GameStateViewModel(app: Application) : AndroidViewModel(app) {

    //Room database
    private val gameDataDao: GameDataDao = GameDataBase.getInstance(app).gameDataDao()
    //LiveData
    val gameStatesLiveData: LiveData<List<GameState>>
            = MutableLiveData<List<GameState>>().apply { value = gameDataDao.getAll() }

    fun saveGameStateToDataBase(vararg gameStates: GameState) = gameDataDao.insert(*gameStates)

    fun deleteGameStateFromDataBase(vararg gameStates : GameState) = gameDataDao.delete(*gameStates)

    fun deleteAllGameStates() = gameDataDao.deleteAll()

    fun updateGameState(gameState: GameState) = gameDataDao.update(gameState)
}