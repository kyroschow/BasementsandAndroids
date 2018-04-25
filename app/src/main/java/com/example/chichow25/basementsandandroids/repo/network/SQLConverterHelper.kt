package com.example.chichow25.basementsandandroids.repo.network

import android.database.Cursor
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by per6 on 4/25/18.
 */
const val TAG = "SQLConverterHelper"
class SQLConverterHelper{
    companion object {
        suspend fun convert(cursor: Cursor){
            cursor.moveToFirst()
            val resultSet = JSONArray()
            while(!cursor.isAfterLast){
                val rowObject = JSONObject()
                for(i: Int in 0..cursor.columnCount){
                    try{
                        if(cursor.getString(i) != null){
                            Log.d(TAG, cursor.getString(i))
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i))
                        }
                        else{
                            rowObject.put(cursor.getColumnName(i), "")
                        }
                    }
                    catch(e: Exception){
                        Log.e(TAG, e.message)
                    }
                    resultSet.put(rowObject)
                    cursor.moveToNext()
                }
                cursor.close()
                Log.d(TAG, resultSet.toString())
            }

        }
    }
}