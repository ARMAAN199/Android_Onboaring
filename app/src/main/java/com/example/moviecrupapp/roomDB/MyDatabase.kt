package com.example.moviecrupapp.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [SavedMovies::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
    abstract class MyDatabase : RoomDatabase() {
        abstract fun movieDao(): MovieDao
    }
