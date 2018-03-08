package com.example.chichow25.basementsandandroids.application

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import android.os.Looper
import com.backendless.Backendless

/**
 * Created by chichow25 on 3/8/18.
 */
class BasementsAndAndroidsApplication : Application() {
    val mManager = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    val mChannel: WifiP2pManager.Channel by lazy {
        mManager.initialize(this, Looper.getMainLooper(), null)
    }
    override fun onCreate() {
        super.onCreate()
        Backendless.initApp(BasementAndAndroidsSettings.APPLICATION_ID, BasementAndAndroidsSettings.SECRET_KEY)
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
    }
}