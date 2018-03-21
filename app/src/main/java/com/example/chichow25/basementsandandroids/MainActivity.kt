package com.example.chichow25.basementsandandroids

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chichow25.basementsandandroids.gamedata.EquipmentCategory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //test
        val model = ViewModelProviders.of(this).get(BnaViewModel::class.java)
        val categories = loadJsonFromAssets<List<EquipmentCategory>>(applicationContext, "equipment_list.json")
    }
}