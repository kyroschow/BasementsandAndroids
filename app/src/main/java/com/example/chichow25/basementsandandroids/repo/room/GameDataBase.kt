package com.example.chichow25.basementsandandroids.repo.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Ryan Liu on 4/9/18.
 */
@Database(entities = [(GameState::class)], version = 1)
abstract class GameDataBase : RoomDatabase() {

    abstract fun gameDataDao(): GameDataDao

    companion object {
        private var INSTANCE: GameDataBase? = null
        const val dbName = "game.db"

        fun getInstance(context: Context): GameDataBase {
            if (INSTANCE == null) {
                synchronized(GameDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GameDataBase::class.java, dbName)
                            .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}