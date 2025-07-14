package com.example.presentation.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.madrid.domain.RecentSearchRepository
import kotlin.time.Duration.Companion.hours

class RecentSearchSyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val recentSearchRepository: RecentSearchRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val oneHourAgo = System.currentTimeMillis() - 1.hours.inWholeMilliseconds
        val currentSearches = recentSearchRepository.getRecentSearches()
        val searchesToRemove = currentSearches.filter { it.timestamp < oneHourAgo }
        
        searchesToRemove.forEach { search ->
            recentSearchRepository.removeRecentSearch(search.id.toString())
        }
        
        return Result.success()
    }
}