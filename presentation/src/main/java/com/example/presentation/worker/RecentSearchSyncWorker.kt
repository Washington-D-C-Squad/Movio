package com.example.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.domain.RecentSearchRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecentSearchSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val recentSearchRepository: RecentSearchRepository by inject()

    override suspend fun doWork(): Result {
        val oneHourAgo = System.currentTimeMillis() - 60 * 60 * 1000
        val currentSearches = recentSearchRepository.getRecentSearches()
        val searchesToRemove = currentSearches.filter { it.timestamp < oneHourAgo }
        
        searchesToRemove.forEach { search ->
            recentSearchRepository.removeRecentSearch(search.id.toString())
        }
        
        return Result.success()
    }
}