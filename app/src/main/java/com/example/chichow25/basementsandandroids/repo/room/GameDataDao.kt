package com.example.chichow25.basementsandandroids.repo.room


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.database.Cursor

/**
 * Created by Ryan Liu on 3/29/18.
 */

@Dao
interface GameDataDao {

    @Query("SELECT * from gameState")
    fun getAll(): LiveData<List<GameState>>
    @Query("SELECT * from gameState")
    fun getCursor(): Cursor
    
    @Insert(onConflict = REPLACE)
    fun insert(vararg gameStates: GameState)

    @Delete
    fun delete(vararg gameStates: GameState)

    @Query("DELETE from gameState")
    fun deleteAll()

    @Update
    fun update(gameState: GameState)
}