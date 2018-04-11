package com.example.chichow25.basementsandandroids.ui.base.presenter

import com.example.chichow25.basementsandandroids.ui.base.interactor.MVPInteractor
import com.example.chichow25.basementsandandroids.ui.base.view.MVPView

/**
 * Created by per6 on 4/9/18.
 */
abstract class BasePresenter<V: MVPView, I: MVPInteractor> internal constructor(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable): MVPPresenter<V, I> {
    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?){
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }
}