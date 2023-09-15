package com.example.moviecrupapp.API

import com.example.moviecrupapp.Modals.RandomMovieResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface API {

    @GET("discover/movie")
    fun getMoviesRandom(
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String,

        @Header("accept") accept : String = "application/json",
        @Header("Authorization") Authorization : String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYTI1YTU0N2NlMmI0ZmEzZWVjMjliZDU3NTNmNWU0NiIsInN1YiI6IjY1MDJlZDYzMWJmMjY2MDEzYTc2MmJmYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.bsW2pkrCbcrjda3_yCUNDA05r-uSMhXpe3Vl2POd_Ks"
    ) : Call<RandomMovieResults>

}