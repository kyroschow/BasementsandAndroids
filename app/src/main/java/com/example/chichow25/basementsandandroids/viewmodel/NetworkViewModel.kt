package com.example.chichow25.basementsandandroids.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pDeviceList
import android.net.wifi.p2p.WifiP2pManager
import com.example.chichow25.basementsandandroids.repo.network.GameReceiver

class NetworkViewModel(application: Application) : AndroidViewModel(application), WifiP2pManager.PeerListListener {

    val intentFilter = IntentFilter().apply {
        addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
        addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
    }
    private lateinit var receiver: GameReceiver
    private val peersLiveData0: MutableLiveData<WifiP2pDeviceList> = MutableLiveData()
    val peersLiveData: LiveData<WifiP2pDeviceList> = peersLiveData0

    override fun onPeersAvailable(peers: WifiP2pDeviceList?) {
        peersLiveData0.apply { value = peers }
    }

    fun setUpReceiver() = with(getApplication<Application>()) {
        val manager = getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
        val channel = manager.initialize(this, mainLooper, null)
        receiver = GameReceiver(manager, channel, this@NetworkViewModel)
        registerReceiver(receiver, intentFilter)
    }

    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().unregisterReceiver(receiver)
    }
}