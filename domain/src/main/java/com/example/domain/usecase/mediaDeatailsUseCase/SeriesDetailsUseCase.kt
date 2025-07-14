package com.example.domain.usecase.mediaDeatailsUseCase

import com.example.domain.entity.Series
import com.example.domain.repository.SeriesDetailsRepository
import kotlinx.coroutines.flow.Flow

class SeriesDetailsUseCase(private val seriesDetailsRepository: SeriesDetailsRepository) {
    suspend fun getSeriesById(seriesId: Int): Series = seriesDetailsRepository.getSeriesById(seriesId)

    suspend fun submitSeriesRating(rating: Float) = seriesDetailsRepository.submitSeriesRating(rating)

    suspend fun addSeriesToFavourites(seriesId: Int) = seriesDetailsRepository.addSeriesToFavourites(seriesId)

    suspend fun getListsCollection(): Flow<List<String>> = seriesDetailsRepository.getListsCollection()

    suspend fun addNewCollection(collection: String) = seriesDetailsRepository.addNewCollection(collection)

    suspend fun addSeriesToList(seriesId: Int,listName: String) = seriesDetailsRepository.addSeriesToList(seriesId,listName)
}