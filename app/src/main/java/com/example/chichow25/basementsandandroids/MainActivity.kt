package com.example.chichow25.basementsandandroids

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.backendless.Backendless

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //test
        Backendless.Persistence
    }

    fun setIsWifiP2pEnabled(isWifiP2pEnabled: Boolean){

    }
}