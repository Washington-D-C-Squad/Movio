package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.madrid.data.dataSource.local.entity.ArtistEntity

@Dao
interface ArtistDao {

    @Upsert
    suspend fun insertArtist(artist: ArtistEntity)

    @Delete
    suspend fun deleteArtist(artist: ArtistEntity)

    @Query("SELECT * FROM ARTIST_TABLE WHERE id = :id")
    suspend fun getArtistById(id: Int): ArtistEntity?

    @Query("SELECT * FROM ARTIST_TABLE WHERE name LIKE :name LIMIT 20 OFFSET :offset")
    suspend fun getArtistByName(name: String , offset: Int): List<ArtistEntity>

    @Query("SELECT * FROM ARTIST_TABLE")
    suspend fun getAllArtists(): List<ArtistEntity>

    @Query("DELETE FROM ARTIST_TABLE")
    suspend fun deleteAllArtists()
}