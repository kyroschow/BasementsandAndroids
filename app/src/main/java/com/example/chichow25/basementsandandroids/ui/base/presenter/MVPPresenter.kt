package com.example.chichow25.basementsandandroids.ui.base.presenter

import com.example.chichow25.basementsandandroids.ui.base.interactor.MVPInteractor
import com.example.chichow25.basementsandandroids.ui.base.view.MVPView

/**
 * Created by per6 on 4/9/18.
 */
interface MVPPresenter<V: MVPView, I: MVPInteractor> {
    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?
}