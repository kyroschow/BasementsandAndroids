package com.example.chichow25.basementsandandroids.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.chichow25.basementsandandroids.repo.retrofit.DndApi

class DndApiViewModel : ViewModel(), DndApi by DndApi.create() {}