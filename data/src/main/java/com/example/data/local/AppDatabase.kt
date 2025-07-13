package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ArtistResultDao
import com.example.data.local.dao.MultiResultDao
import com.example.data.local.entity.ArtistResultEntity
import com.example.data.local.entity.MultiResultEntity

@Database(
    entities = [ArtistResultEntity::class, MultiResultEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistResultDao(): ArtistResultDao
    abstract fun multiResultDao(): MultiResultDao
} 