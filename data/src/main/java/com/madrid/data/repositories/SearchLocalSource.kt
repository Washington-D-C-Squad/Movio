package com.madrid.data.repositories

import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity

interface SearchLocalSource {

    fun getMoviesByTitle(query: String): List<MovieEntity>

    fun getSeriesByTitle(query: String): List<SeriesEntity>

    fun getArtistsByTitle(query: String): List<ArtistEntity>


}