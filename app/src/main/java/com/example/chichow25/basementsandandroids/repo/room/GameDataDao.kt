package com.example.chichow25.basementsandandroids.repo.room


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import android.content.Intent
import android.util.Log
import com.example.chichow25.basementsandandroids.presenter.MainActivity
import com.example.chichow25.basementsandandroids.util.convert

/**
 * Created by Ryan Liu on 3/29/18.
 */

//@Dao
//interface GameDataDao {
//
//    @Query("SELECT * from gameState")
//    fun getAll(): LiveData<List<GameState>>
//    @Query("SELECT * from gameState")
//    fun getCursor(): Cursor
//
//    @Insert(onConflict = REPLACE)
//    fun insert(vararg gameStates: GameState)
//
//    @Delete
//    fun delete(vararg gameStates: GameState)
//
//    @Query("DELETE from gameState")
//    fun deleteAll()
//
//    @Update
//    fun update(gameState: GameState)
//}

@Dao
abstract class GameDataDao {
    @Query("SELECT * from gameState")
    abstract fun getAll(): LiveData<List<GameState>>

    suspend fun convertCursor() {
        val databaseStorage = DatabaseStorage.gameDatabase
        databaseStorage.query(null,null).convert()
        Log.d("GameDataDao", "DB converted")
    }

    fun insert(gameState: GameState){
        val sqlRequest = "INSERT INTO gameState(playerPositionX, playerPositionY, enemyPositionX, enemyPositionY, playerHealth, enemyHealth, initiative, map, pieces) " +
                "SELECT ?, ?, ?, ?, ?, ?, ?, ?, ? " +
                "WHERE NOT EXISTS(SELECT 1 FROM (SELECT * FROM gameState ORDER BY id DESC LIMIT 1) WHERE playerPositionX = ?);"
        val database = DatabaseStorage.gameDatabase.openHelper.writableDatabase
        database.execSQL(sqlRequest, arrayOf(gameState.playerPositionX, gameState.playerPositionY, gameState.enemyPositionX, gameState.enemyPositionY,
                gameState.playerHealth, gameState.enemyHealth, gameState.initiative, gameState.map, gameState.pieces))
        MainActivity().applicationContext.sendBroadcast(Intent(DatabaseChangedReceiver.ACTION_DATABASE_CHANGED))
    }

    @Delete
    abstract fun delete(vararg gameStates: GameState)

    @Query("DELETE from gameState")
    abstract fun deleteAll()

    @Update
    abstract fun update(gameState: GameState)
}