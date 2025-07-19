package com.madrid.domain.usecase.mediaDeatailsUseCase

import com.madrid.domain.entity.Cast
import com.madrid.domain.entity.Episode
import com.madrid.domain.entity.Review
import com.madrid.domain.entity.Series
import com.madrid.domain.entity.SimilarSeries
import com.madrid.domain.entity.Trailer
import com.madrid.domain.repository.SeriesDetailsRepository
import kotlinx.coroutines.flow.Flow

class SeriesDetailsUseCase(private val seriesDetailsRepository: SeriesDetailsRepository) {

    suspend fun getSeriesDetailsById(seriesId: Int): Series =
        seriesDetailsRepository.getSeriesDetailsById(seriesId)

    suspend fun getSeriesTrailersById(seriesId: Int): Trailer =
        seriesDetailsRepository.getSeriesTrailersById(seriesId)

    suspend fun getSeriesCreditsById(seriesId: Int): List<Cast> =
        seriesDetailsRepository.getSeriesCreditsById(seriesId)

    suspend fun getSeriesReviewsById(seriesId: Int): List<Review> =
        seriesDetailsRepository.getSeriesReviewsById(seriesId)

    suspend fun getSimilarSeriesById(seriesId: Int): List<SimilarSeries> =
        seriesDetailsRepository.getSimilarSeriesById(seriesId)

    suspend fun getEpisodesBySeasonId(seasonId: Int, seasonNumber: Int): List<Episode> =
        seriesDetailsRepository.getEpisodesBySeasonId(seasonId, seasonNumber)


    suspend fun submitSeriesRating(rating: Float) = seriesDetailsRepository.submitSeriesRating(rating)

    suspend fun addSeriesToFavourites(seriesId: Int) = seriesDetailsRepository.addSeriesToFavourites(seriesId)

    suspend fun getListsCollection(): Flow<List<String>> = seriesDetailsRepository.getListsCollection()

    suspend fun addNewCollection(collection: String) = seriesDetailsRepository.addNewCollection(collection)

    suspend fun addSeriesToList(seriesId: Int,listName: String) = seriesDetailsRepository.addSeriesToList(seriesId,listName)
}