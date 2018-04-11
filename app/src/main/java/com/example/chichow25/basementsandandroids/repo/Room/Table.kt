package com.example.chichow25.basementsandandroids.repo.Room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Ryan Liu on 3/29/18.
 */
@Entity(tableName = "gameState")
data class GameState(@PrimaryKey(autoGenerate = true) var id: Long?,
                       @ColumnInfo(name = "playerPositions") var playerPositionX:Int, var playerPositionY: Int,
                       @ColumnInfo(name = "enemyPositions") var enemyPositionX:Int , var enemyPositionY: Int,
                       @ColumnInfo(name = "playerHealth") var playerHealth: Int,
                       @ColumnInfo(name = "enemyHealth") var enemyHealth: Int,
                       @ColumnInfo(name = "initiative") var initiative: Int,
                       @ColumnInfo(name = "map") var map: String,
                       @ColumnInfo(name = "setPieces") var pieces: String)