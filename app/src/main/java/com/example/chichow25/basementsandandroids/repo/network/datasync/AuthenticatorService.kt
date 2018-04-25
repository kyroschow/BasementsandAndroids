package com.example.chichow25.basementsandandroids.repo.network.datasync

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by testaccount on 4/24/18.
 */
class AuthenticatorService: Service(){
    private val authenticator = Authenticator(this)

    override fun onBind(intent: Intent?): IBinder {
        return authenticator.iBinder
    }

}