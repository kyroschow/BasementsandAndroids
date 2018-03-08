package com.example.chichow25.basementsandandroids.application

import android.app.Application
import com.backendless.Backendless
import com.example.chichow25.basementsandandroids.application.BasementAndAndroidsSettings

/**
 * Created by chichow25 on 3/8/18.
 */
class BasementsAndAndroidsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Backendless.initApp(BasementAndAndroidsSettings.APPLICATION_ID, BasementAndAndroidsSettings.SECRET_KEY)
    }
}