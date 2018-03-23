package com.example.chichow25.basementsandandroids

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.util.Log
import android.view.WindowManager
import com.example.chichow25.basementsandandroids.gamedata.EquipmentCategory
import kotlinx.android.synthetic.main.activity_main_splash.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //viewmodel
        val model = ViewModelProviders.of(this).get(BnaViewModel::class.java)
        //json data
        val categories = applicationContext.loadEquipmentCategoriesList()

        Handler().postDelayed({
            val constraintSet = ConstraintSet()
            constraintSet.clone(this@MainActivity, R.layout.activity_main_selector)
            TransitionManager.beginDelayedTransition(mainActivityLayout)
            constraintSet.applyTo(mainActivityLayout)
        }, 5000)
    }

    fun EquipmentCategory.getEquipmentIndexes() = equipment.map { it.url.substringAfterLast('/').toInt() }
}