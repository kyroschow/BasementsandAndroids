package com.example.chichow25.basementsandandroids.ui.splash.view

import android.content.Intent
import com.example.chichow25.basementsandandroids.presenter.MainActivity
import com.example.chichow25.basementsandandroids.ui.base.view.BaseActivity
import com.example.chichow25.basementsandandroids.ui.splash.interactor.SplashMVPInteractor
import com.example.chichow25.basementsandandroids.ui.splash.presenter.SplashMVPPresenter
import javax.inject.Inject

/**
 * Created by per6 on 4/17/18.
 */
class SplashActivity: BaseActivity(), SplashMVPView {

    @Inject
    lateinit var presenter: SplashMVPPresenter<SplashMVPView, SplashMVPInteractor>

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showSuccessToast() {

    }

    override fun showErrorToast() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun openMainActivity() {

    }
    //add

}