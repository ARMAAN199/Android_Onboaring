package com.example.moviecrupapp.repositories

import com.example.moviecrupapp.API.API
import com.example.moviecrupapp.Modals.Genres
import com.example.moviecrupapp.Modals.RandomMovieResults
import com.example.moviecrupapp.roomDB.MovieDao
import javax.inject.Inject

class MovieRepository @Inject constructor(val API : API) {

    suspend fun getMoviesRandom() : RandomMovieResults {
        return API.getMoviesRandom(
            includeAdult = true,
            includeVideo = false,
            language = "en-US",
            page = 1,
            sortBy = "popularity.desc"
        )
    }

    suspend fun getGenreList() : Genres {
        return API.getGenreList()
    }

}