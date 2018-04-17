package com.example.chichow25.basementsandandroids.ui.splash.view

import com.example.chichow25.basementsandandroids.ui.base.view.MVPView

/**
 * Created by per6 on 4/17/18.
 */
interface SplashMVPView : MVPView {
    fun showSuccessToast()
    fun showErrorToast()
    fun openMainActivity()
}