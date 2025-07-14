package com.madrid.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.madrid.domain.usecase.searchUseCase.RecentSearchUseCase

class RecentSearchSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val recentSearchUseCase: RecentSearchUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        recentSearchUseCase.clearAllRecentSearches()
        return Result.success()
    }
}