package com.example.chichow25.basementsandandroids.repo.room


import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE

/**
 * Created by Ryan Liu on 3/29/18.
 */

@Dao
interface GameDataDao {

    @Query("SELECT * from gameState")
    fun getAll(): List<GameState>

    @Insert(onConflict = REPLACE)
    fun insert(vararg gameStates: GameState)

    @Delete
    fun delete(vararg gameStates: GameState)

    @Query("DELETE from gameState")
    fun deleteAll()

    @Update
    fun update(gameState: GameState)
}