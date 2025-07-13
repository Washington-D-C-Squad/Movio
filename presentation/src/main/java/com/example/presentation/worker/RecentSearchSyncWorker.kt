package com.example.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.presentation.component.searchListUi.SearchListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecentSearchSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val searchListViewModel: SearchListViewModel by inject()

    override suspend fun doWork(): Result {
        searchListViewModel.removeOlderThanOneHour()
        return Result.success()
    }
}