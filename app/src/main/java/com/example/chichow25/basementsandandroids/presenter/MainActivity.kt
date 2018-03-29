package com.example.chichow25.basementsandandroids.presenter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.chichow25.basementsandandroids.R

class MainActivity : AppCompatActivity(), SplashScreenFragment.EventHandler {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //settings don't touch, for full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //replace fragments only when the activity is started
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(R.id.mainActivityContainer, SplashScreenFragment()).commit()
    }

    override fun joinPlayer(v: View) {
        //TODO: find games for player
    }

    override fun hostPlayer(v: View) {
        //TODO: host a game as DM
    }
}