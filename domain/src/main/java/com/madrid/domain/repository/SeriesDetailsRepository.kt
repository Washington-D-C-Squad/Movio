package com.madrid.domain.repository

import com.madrid.domain.entity.Series
import kotlinx.coroutines.flow.Flow

interface SeriesDetailsRepository {
    suspend fun getSeriesById(seriesId: Int): Series
    suspend fun submitSeriesRating(rating: Float)
    suspend fun addSeriesToFavourites(seriesId: Int)
    suspend fun getListsCollection(): Flow<List<String>>
    suspend fun addNewCollection(collection: String)
    suspend fun addSeriesToList(seriesId: Int,listName: String): Boolean
}