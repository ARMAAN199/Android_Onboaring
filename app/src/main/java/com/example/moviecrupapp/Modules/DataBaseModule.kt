package com.example.moviecrupapp.Modules

import android.app.Application
import androidx.room.Room
import com.example.moviecrupapp.MyApplication
import com.example.moviecrupapp.roomDB.MovieDao
import com.example.moviecrupapp.roomDB.MyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun getDBInstance(application: MyApplication): MyDatabase {
        return Room.databaseBuilder(application, MyDatabase::class.java, "MyDatabase")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMovieDao(database: MyDatabase): MovieDao {
        return database.movieDao()
    }

}