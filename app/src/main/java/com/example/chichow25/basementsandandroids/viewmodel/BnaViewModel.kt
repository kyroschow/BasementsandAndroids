package com.example.chichow25.basementsandandroids.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.chichow25.basementsandandroids.repo.retrofit.DndApi
import io.realm.Realm

/**
 * Created by chichow25 on 3/19/18.
 */
class BnaViewModel : ViewModel(), DndApi by DndApi.create() {

    private val realm = Realm.getDefaultInstance()
}