package com.madrid.domain.usecase.mediaDeatailsUseCase

import com.madrid.domain.entity.Series
import com.madrid.domain.repository.SeriesDetailsRepository
import kotlinx.coroutines.flow.Flow

class SeriesDetailsUseCase(private val seriesDetailsRepository: SeriesDetailsRepository) {
    suspend fun getSeriesById(seriesId: Int): Series =
        seriesDetailsRepository.getSeriesDetailsById(seriesId)

    suspend fun submitSeriesRating(rating: Float) = seriesDetailsRepository.submitSeriesRating(rating)

    suspend fun addSeriesToFavourites(seriesId: Int) = seriesDetailsRepository.addSeriesToFavourites(seriesId)

    suspend fun getListsCollection(): Flow<List<String>> = seriesDetailsRepository.getListsCollection()

    suspend fun addNewCollection(collection: String) = seriesDetailsRepository.addNewCollection(collection)

    suspend fun addSeriesToList(seriesId: Int,listName: String) = seriesDetailsRepository.addSeriesToList(seriesId,listName)
}