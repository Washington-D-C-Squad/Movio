package com.example.domain.interfaces

import com.example.domain.entity.Artist
import com.example.domain.entity.Movie
import com.example.domain.entity.Series
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getAllMovie(): List<Movie>
    suspend fun getAllSeries(): List<Series>
    suspend fun getAllArtist(): List<Artist>
    suspend fun getTopRatedMovie(): List<Movie>
    suspend fun getTopRatedSeries(): List<Series>
    suspend fun getTopResults(query: String): List<Movie>
    suspend fun getSearchedMovie(query: String): Flow <List<Movie>>
    suspend fun getSearchedSeries(query: String): List<Series>
    suspend fun getSearchedArtists(query: String): List<Artist>
}