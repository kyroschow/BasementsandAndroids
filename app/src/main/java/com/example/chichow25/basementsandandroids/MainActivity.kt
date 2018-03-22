package com.example.chichow25.basementsandandroids

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chichow25.basementsandandroids.gamedata.EquipmentCategory
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //test
        val model = ViewModelProviders.of(this).get(BnaViewModel::class.java)
        val categories = loadJsonFromAssets<List<EquipmentCategory>>(applicationContext, "equipment_list.json")
//        Log.d(TAG, categories[0].getEquipmentIndexes().toString())
        Log.d(TAG, categories[0].javaClass.name)
        Log.d(TAG, categories[0].getEquipmentIndexes().javaClass.name)


        for (index in categories[0].getEquipmentIndexes()) {
            //weapons
            launch {
                val weapon = model.getWeaponAt(index).await()
                Log.d(TAG, "Weapon: $weapon")
            }
        }
    }

    fun EquipmentCategory.getEquipmentIndexes() : List<Int> = equipment.map {
        val string = it.url.substringAfterLast('/')
        Log.d(TAG, string)
        return@map string.toInt()
    }
}