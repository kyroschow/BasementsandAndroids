package com.example.chichow25.basementsandandroids.repo.room

import android.arch.persistence.room.Room
import com.example.chichow25.basementsandandroids.presenter.MainActivity

/**
 * Created by per6 on 5/1/18.
 */
object DatabaseStorage {
    val gameDatabase: GameDataBase = Room.databaseBuilder(MainActivity().applicationContext, GameDataBase::class.java, "gameDatabase").build()
}