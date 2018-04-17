package com.example.chichow25.basementsandandroids.ui.splash.presenter

import com.example.chichow25.basementsandandroids.ui.base.presenter.MVPPresenter
import com.example.chichow25.basementsandandroids.ui.splash.interactor.SplashMVPInteractor
import com.example.chichow25.basementsandandroids.ui.splash.view.SplashMVPView
import org.reactivestreams.Subscription

/**
 * Created by per6 on 4/17/18.
 */
interface SplashMVPPresenter<V: SplashMVPView, I: SplashMVPInteractor>: MVPPresenter<V,I>{
    fun getArmor(index: Int): Subscription
    fun getWeapon(index: Int): Subscription
    fun getMonster(index: Int): Subscription
}