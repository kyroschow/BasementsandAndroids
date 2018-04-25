package com.example.chichow25.basementsandandroids.util

import android.database.Cursor
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by per6 on 4/25/18.
 */
const val TAG = "Cursor"
fun Cursor.convert(){
    this.moveToFirst()
    val resultSet = JSONArray()
    while(!this.isAfterLast){
        val rowObject = JSONObject()
        for(i: Int in 0..this.columnCount){
            try{
                if (this.getString(i) != null){
                    Log.d(TAG,this.getString(i))
                    rowObject.put(this.getColumnName(i),this.getString(i))
                }
                else{
                    rowObject.put(this.getColumnName(i), "")
                }
            }
            catch(e: Exception){
                Log.e(TAG, e.message)
            }
            resultSet.put(rowObject)
            this.moveToNext()
        }
        this.close()
        Log.d(TAG, resultSet.toString())
    }
}