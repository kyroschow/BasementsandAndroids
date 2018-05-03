package com.example.chichow25.basementsandandroids.repo.room

import com.example.chichow25.basementsandandroids.presenter.MainActivity

/**
 * Created by per6 on 5/1/18.
 */
object DatabaseStorage {
    //TODO: Make this work
    val gameDatabase: GameDataBase = GameDataBase.getInstance(MainActivity().application)
}