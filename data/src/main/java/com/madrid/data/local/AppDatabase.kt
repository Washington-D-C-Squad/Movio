package com.madrid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madrid.data.local.dao.ArtistResultDao
import com.madrid.data.local.dao.MultiResultDao
import com.madrid.data.local.entity.ArtistResultEntity
import com.madrid.data.local.entity.MultiResultEntity

@Database(
    entities = [ArtistResultEntity::class, MultiResultEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistResultDao(): ArtistResultDao
    abstract fun multiResultDao(): MultiResultDao
} 