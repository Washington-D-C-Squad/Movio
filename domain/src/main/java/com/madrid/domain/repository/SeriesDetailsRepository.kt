package com.madrid.domain.repository

import com.madrid.domain.entity.Artist
import com.madrid.domain.entity.Movie
import com.madrid.domain.entity.Review
import com.madrid.domain.entity.Season
import com.madrid.domain.entity.Series

interface SeriesDetailsRepository {
    suspend fun getSeriesById(seriesId: Int): Series
    suspend fun submitSeriesRating(rating: Float)
    suspend fun addSeriesToFavourites(seriesId: Int)
    suspend fun getListsCollection(): List<String>
    suspend fun addNewCollection(collection: String)
    suspend fun addSeriesToList(seriesId: Int,listName: String): Boolean
    suspend fun getTopCast(seriesId: Int): List<Artist>
    suspend fun getCurrentSeasons(seriesId: Int): List<Season>
    suspend fun getReviews(seriesId: Int): List<Review>
    suspend fun getSimilarSeries(genre: List<String>): List<Movie>
}