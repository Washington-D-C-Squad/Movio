package com.madrid.data.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.madrid.data.dataSource.local.dao.ArtistDao
import com.madrid.data.dataSource.local.dao.MovieGenreDao
import com.madrid.data.dataSource.local.dao.MovieDao
import com.madrid.data.dataSource.local.dao.RecentSearchDao
import com.madrid.data.dataSource.local.dao.SeriesDao
import com.madrid.data.dataSource.local.dao.SeriesGenreDao
import com.madrid.data.dataSource.local.entity.ArtistEntity
import com.madrid.data.dataSource.local.entity.MovieGenreEntity
import com.madrid.data.dataSource.local.entity.SeriesGenreEntity
import com.madrid.data.dataSource.local.entity.MovieEntity
import com.madrid.data.dataSource.local.entity.SeriesEntity
import com.madrid.data.dataSource.local.entity.RecentSearchEntity
import com.madrid.data.dataSource.local.entity.relationship.MovieGenreCrossRef
import com.madrid.data.dataSource.local.entity.relationship.SeriesGenreCrossRef

@Database(
    entities = [
        MovieEntity::class,
        SeriesEntity::class,
        MovieGenreEntity::class,
        SeriesGenreEntity::class,
        ArtistEntity::class,
        RecentSearchEntity::class,
        MovieGenreCrossRef::class,
        SeriesGenreCrossRef::class
    ],
    version = 1
)
abstract class MovioDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun seriesDao(): SeriesDao
    abstract fun movieGenreDao(): MovieGenreDao
    abstract fun seriesGenreDao(): SeriesGenreDao
    abstract fun artistDao(): ArtistDao
    abstract fun recentSearchDao(): RecentSearchDao

    companion object {
        const val DATABASE_NAME = "MOVIO_DATABASE"

        @Volatile
        private var instance: MovioDatabase? = null

        fun getInstance(context: Context): MovioDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        fun getInstanceWithoutContext(): MovioDatabase {
            return instance
                ?: throw IllegalStateException("Database not initialized. Call getInstance(context) first.")
        }

        private fun buildDatabase(context: Context): MovioDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovioDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration(true) //TODO: Check Migration strategy
                .build()
        }
    }
}