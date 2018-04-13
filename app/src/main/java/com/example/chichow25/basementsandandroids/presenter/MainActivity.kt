package com.example.chichow25.basementsandandroids.presenter

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.chichow25.basementsandandroids.R
import com.example.chichow25.basementsandandroids.repo.network.GameReceiver
import com.example.chichow25.basementsandandroids.viewmodel.GameStateViewModel
import io.realm.Realm

class MainActivity : AppCompatActivity(), SplashScreenFragment.EventHandler, WifiP2pManager.PeerListListener, MainActivityView {

    private val TAG = "MainActivity"
    //network
    private lateinit var receiver: GameReceiver
    private val intentFilter = IntentFilter().apply {
        addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //settings don't touch, for full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //replace fragments only when the activity is started
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(R.id.mainActivityContainer, SplashScreenFragment()).commit()
        val presenter = MainActivityPresenter(this)

        //network
        val manager = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
        val channel = manager.initialize(this, mainLooper, null)
        receiver = GameReceiver(manager, channel, this)

        //game states from ViewModel
        //TODO: fix viewmodel
        val factory = ViewModelProvider.AndroidViewModelFactory(application)
        val gameStateViewModel = ViewModelProviders.of(this, factory).get(GameStateViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        //network
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()

        //network
        unregisterReceiver(receiver)
    }

    override fun joinPlayer(v: View) {
        //TODO: find games for player
    }

    override fun hostPlayer(v: View) {
        //TODO: host a game as DM
    }

    override fun onPeersAvailable(peers: WifiP2pDeviceList?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}