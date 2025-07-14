package com.example.data.dataSource.local.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dataSource.local.data.dao.MovieDao
import com.example.data.dataSource.local.data.entity.ArtistEntity
import com.example.data.dataSource.local.data.entity.MovieCategoryEntity
import com.example.data.dataSource.local.data.entity.MovieEntity
import com.example.data.dataSource.local.data.entity.SeriesEntity

@Database(
    entities = [
        MovieEntity::class,
        SeriesEntity::class,
        MovieCategoryEntity::class,
        ArtistEntity::class
    ],
    version = 1
)
abstract class MovioDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "movie_database"

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