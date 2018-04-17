package com.example.chichow25.basementsandandroids.repo.room

import android.os.Handler
import android.os.HandlerThread

/**
 * Created by per6 on 4/9/18.
 */

class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerHandler.post(task)
    }

}