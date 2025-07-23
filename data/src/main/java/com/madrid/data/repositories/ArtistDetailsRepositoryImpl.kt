package com.madrid.data.repositories

import com.madrid.data.dataSource.remote.mapper.toArtisKnownFor
import com.madrid.data.dataSource.remote.mapper.toArtist
import com.madrid.data.repositories.remote.RemoteDataSource
import com.madrid.domain.entity.ArtisKnownFor
import com.madrid.domain.entity.Artist
import com.madrid.domain.repository.ArtistDetailsRepository

class ArtistDetailsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ArtistDetailsRepository {
    override suspend fun getArtistDetailsById(artistId: Int): Artist {
        return remoteDataSource.getArtistDetailsById(artistId).toArtist()
    }
    override suspend fun getArtistKnownForById(artistId: Int): List<ArtisKnownFor> {
        return remoteDataSource.getArtistKnownForById(artistId).toArtisKnownFor().knownForMovies
            ?: emptyList()
    }
}