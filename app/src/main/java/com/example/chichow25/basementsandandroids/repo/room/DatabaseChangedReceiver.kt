package com.example.chichow25.basementsandandroids.repo.room

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by per6 on 5/1/18.
 */
class DatabaseChangedReceiver(): BroadcastReceiver() {
    companion object {
        const val ACTION_DATABASE_CHANGED = "com.example.chichow25.basementsandandroids.DATABASE_CHANGED"
    }
    override fun onReceive(context: Context?, intent: Intent?) {

    }

}