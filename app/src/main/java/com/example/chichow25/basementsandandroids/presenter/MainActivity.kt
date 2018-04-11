package com.example.chichow25.basementsandandroids.presenter

import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.example.chichow25.basementsandandroids.R
import io.realm.Realm

class MainActivity : AppCompatActivity(), SplashScreenFragment.EventHandler, WifiP2pManager.PeerListListener, MainActivityView {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initialize realm
        Realm.init(this)
        //settings don't touch, for full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //replace fragments only when the activity is started
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(R.id.mainActivityContainer, SplashScreenFragment()).commit()
        val presenter = MainActivityPresenter(this)
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