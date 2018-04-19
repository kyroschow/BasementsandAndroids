package com.example.chichow25.basementsandandroids.presenter

import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chichow25.basementsandandroids.R
import com.example.chichow25.basementsandandroids.databinding.ActivityMainSplashBinding
import kotlinx.android.synthetic.main.activity_main_splash.*

/**
 * Created by chichow25 on 3/27/18.
 */
class SplashScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ActivityMainSplashBinding.inflate(inflater, container, false)
        activity?.let {
            require(activity is EventHandler) { "Must implement ${EventHandler::class.java.name}" }
            binding.handler = it as EventHandler
        }
        //after bind, return the view inflated by bind
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //test load image into imageview
        Glide.with(view)
                .load("https://png.pngtree.com/thumb_back/fw800/back_pic/04/22/94/145833a51e31a1e.jpg")
                .into(backgroundImageView)

        //test for the constraint set animations
        Handler().postDelayed({
            val cs = ConstraintSet()
            cs.clone(context, R.layout.activity_main_selector)
            TransitionManager.beginDelayedTransition(mainActivityLayout)
            cs.applyTo(mainActivityLayout)
        }, 5000)
    }

    interface EventHandler {

        fun joinPlayer(v: View)

        fun hostPlayer(v: View)
    }
}