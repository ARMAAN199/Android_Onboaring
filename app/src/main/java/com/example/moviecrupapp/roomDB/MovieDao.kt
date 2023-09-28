package com.example.moviecrupapp.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {
    @Insert
    fun insertMovie(movie : SavedMovies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<SavedMovies>)

    @Query("Delete from savedmovies where id = :id")
    fun removeMovie(id: Int)

    @Query("Delete from savedmovies where id in (:ids)")
    suspend fun removeMovies(ids: List<Int>)

    @Query("Select id from savedmovies")
    fun getSavedMovieIds() : LiveData<List<Int>>

    @Query("Select * from savedmovies where id = :id")
    fun getSavedMovie(id : Int) : SavedMovies

    @Update
    fun updateMovie(movie: SavedMovies)

}