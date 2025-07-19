package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.mapper.toCredits
import com.madrid.data.dataSource.remote.mapper.toEpisode
import com.madrid.data.dataSource.remote.mapper.toReviewResult
import com.madrid.data.dataSource.remote.mapper.toSeries
import com.madrid.data.dataSource.remote.mapper.toSimilarSeries
import com.madrid.data.dataSource.remote.mapper.toTrailer
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.Cast
import com.madrid.domain.entity.Episode
import com.madrid.domain.entity.Review
import com.madrid.domain.entity.Series
import com.madrid.domain.entity.SimilarSeries
import com.madrid.domain.entity.Trailer
import com.madrid.domain.repository.SeriesDetailsRepository
import kotlinx.coroutines.flow.Flow

class SeriesDetailsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : SeriesDetailsRepository {

    //region series details
    override suspend fun getSeriesDetailsById(seriesId: Int): Series {
        return remoteDataSource.getSeriesDetailsById(seriesId).toSeries()
    }

    override suspend fun getSeriesTrailersById(seriesId: Int): Trailer {
        return remoteDataSource.getSeriesTrailersById(seriesId).toTrailer()
    }

    override suspend fun getSeriesCreditsById(seriesId: Int): List<Cast> {
        return remoteDataSource.getSeriesCreditsById(seriesId).toCredits().cast ?: emptyList()
    }

    override suspend fun getSeriesReviewsById(seriesId: Int): List<Review> {
        return remoteDataSource.getSeriesReviewsById(seriesId).toReviewResult().results
            ?: emptyList()
    }

    override suspend fun getSimilarSeriesById(seriesId: Int): List<SimilarSeries> {
        return remoteDataSource.getSimilarSeriesById(seriesId).results?.map { it.toSimilarSeries() }
            ?: emptyList()
    }

    override suspend fun getEpisodesBySeasonId(seasonId: Int, seasonNumber: Int): List<Episode> {
        return remoteDataSource.getEpisodesBySeasonId(
            seriesId = seasonId,
            seasonNumber = seasonNumber
        ).episodeNetworks?.map { it.toEpisode() } ?: emptyList()
    }
    //End region


    override suspend fun submitSeriesRating(rating: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun addSeriesToFavourites(seriesId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getListsCollection(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNewCollection(collection: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addSeriesToList(seriesId: Int, listName: String): Boolean {
        TODO("Not yet implemented")
    }
}