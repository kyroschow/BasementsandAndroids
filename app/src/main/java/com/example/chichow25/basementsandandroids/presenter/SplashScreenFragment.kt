package com.example.chichow25.basementsandandroids.presenter

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chichow25.basementsandandroids.R
import com.example.chichow25.basementsandandroids.databinding.ActivityMainSplashBinding
import com.example.chichow25.basementsandandroids.viewmodel.BnaViewModel
import kotlinx.android.synthetic.main.activity_main_splash.*

/**
 * Created by chichow25 on 3/27/18.
 */
class SplashScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ActivityMainSplashBinding.inflate(inflater, container, false)
        activity?.let {
            try {
                binding.handler = it as EventHandler
            } catch (e: ClassCastException) {
                //activity must implement the interface to use the fragment
                throw ClassCastException("Must implement ${EventHandler::class.java.name}")
            }
        }
        //after bind, return the view inflated by bind
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Handler().postDelayed({
            val cs = ConstraintSet()
            cs.clone(context, R.layout.activity_main_selector)
            TransitionManager.beginDelayedTransition(mainActivityLayout)
            cs.applyTo(mainActivityLayout)

        }, 5000)
    }

    interface EventHandler {

        fun joinPlayer(v : View);

        fun hostPlayer(v : View);
    }
}