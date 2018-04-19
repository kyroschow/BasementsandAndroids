package com.example.chichow25.basementsandandroids.presenter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.chichow25.basementsandandroids.R
import com.example.chichow25.basementsandandroids.repo.room.GameState
import com.example.chichow25.basementsandandroids.viewmodel.DndApiViewModel
import com.example.chichow25.basementsandandroids.viewmodel.GameStateViewModel
import com.example.chichow25.basementsandandroids.viewmodel.NetworkViewModel
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity(), SplashScreenFragment.EventHandler, MainActivityView {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //settings don't touch, for full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //replace fragments only when the activity is started
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(R.id.mainActivityContainer, SplashScreenFragment()).commit()
        val presenter = MainActivityPresenter(this)

        //ViewModels
        val factory = ViewModelProvider.AndroidViewModelFactory(application)
        val gameStateViewModel = ViewModelProviders.of(this, factory).get(GameStateViewModel::class.java)
        val dndApiViewModel = ViewModelProviders.of(this, factory).get(DndApiViewModel::class.java)
        val networkViewModel = ViewModelProviders.of(this, factory).get(NetworkViewModel::class.java)

        launch {
            dndApiViewModel.getWeaponsAsync().let {
                Log.d(TAG, it.toString())
            }
        }
    }

    override fun joinPlayer(v: View) {
        //TODO: find games for player
    }

    override fun hostPlayer(v: View) {
        //TODO: host a game as DM

    }
}