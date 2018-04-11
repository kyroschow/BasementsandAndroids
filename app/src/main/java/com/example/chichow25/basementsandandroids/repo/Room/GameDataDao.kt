package com.example.chichow25.basementsandandroids.repo.Room


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by Ryan Liu on 3/29/18.
 */

@Dao
interface GameDataDao {

    @Query("SELECT * from gameState")
    fun getAll(): Flowable<List<GameState>>

    @Insert(onConflict = REPLACE)
    fun insert(GameState: GameState)

    @Query("DELETE from gameState")
    fun deleteAll()
}