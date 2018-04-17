package com.example.chichow25.basementsandandroids.ui.splash.presenter

import com.example.chichow25.basementsandandroids.ui.base.presenter.BasePresenter
import com.example.chichow25.basementsandandroids.ui.splash.interactor.SplashMVPInteractor
import com.example.chichow25.basementsandandroids.ui.splash.view.SplashMVPView
import com.example.chichow25.basementsandandroids.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription
import javax.inject.Inject

/**
 * Created by per6 on 4/17/18.
 */
class SplashPresenter<V: SplashMVPView, I: SplashMVPInteractor> @Inject internal constructor(interactor: I, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable): BasePresenter<V,I>(interactor = interactor, schedulerProvider = schedulerProvider, compositeDisposable = disposable), SplashMVPPresenter<V,I>{

    override fun onAttach(view: V?) {
        super.onAttach(view)

    }

    override fun getArmor(index: Int): Subscription {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeapon(index: Int): Subscription {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMonster(index: Int): Subscription {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}