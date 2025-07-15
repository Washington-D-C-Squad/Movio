package com.madrid.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.madrid.data.dataSource.local.MovioDatabase

class RecentSearchSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        try {
            val database = MovioDatabase.getInstance(applicationContext)
//            database.recentSearchDao().deleteAllRecentSearches()
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}