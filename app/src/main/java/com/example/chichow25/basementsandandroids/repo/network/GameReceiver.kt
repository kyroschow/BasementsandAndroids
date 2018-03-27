package com.example.chichow25.basementsandandroids.repo.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.chichow25.basementsandandroids.presenter.MainActivity

/**
 * Created by per6 on 3/8/18.
 */
class GameReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val activity = MainActivity()
        //TODO: Implement onReceive
    }
}