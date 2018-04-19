package com.example.chichow25.basementsandandroids.repo.network.datasync

import android.accounts.Account
import android.content.AbstractThreadedSyncAdapter
import android.content.ContentProviderClient
import android.content.Context
import android.content.SyncResult
import android.os.Bundle

/**
 * Created by per6 on 4/19/18.
 */
class SyncAdapter(context: Context, autoInitialize: Boolean, allowParallelSyncs: Boolean): AbstractThreadedSyncAdapter(context, autoInitialize, allowParallelSyncs){
    init {
        val mContentResolver = context.contentResolver
    }
    override fun onPerformSync(account: Account?, extras: Bundle?, authority: String?, provider: ContentProviderClient?, syncResult: SyncResult?) {
        //TODO: Implement data transfer
    }

}