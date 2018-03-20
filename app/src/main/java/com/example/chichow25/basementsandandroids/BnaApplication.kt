package com.example.chichow25.basementsandandroids

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by chichow25 on 3/8/18.
 */
class BnaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("bna.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }
}