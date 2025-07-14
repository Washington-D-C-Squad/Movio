package com.madrid.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {

    @Insert
    suspend fun insertArtist(artist: ArtistEntity)

    @Delete
    suspend fun deleteArtist(artist: ArtistEntity)

    @Query("SELECT * FROM ARTIST_TABLE WHERE id = :id")
    fun getArtistById(id: Int): Flow<ArtistEntity?>

    @Query("SELECT * FROM ARTIST_TABLE WHERE name LIKE :name")
    fun getArtistByName(name: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM ARTIST_TABLE")
    fun getAllArtists(): Flow<List<ArtistEntity>>

    @Query("DELETE FROM ARTIST_TABLE")
    suspend fun deleteAllArtists()
}