package com.example.moviecrupapp.Adapters

import com.example.moviecrupapp.Modals.Result
import com.example.moviecrupapp.roomDB.SavedMovies

interface LayoutHandlers {
    fun onAddButtonClick(movie: SavedMovies)

    fun onRemoveButtonClick(id: Int)

    fun retrofitMovieToRoomMovieConverter(movie: Result): SavedMovies {
        return SavedMovies(
            adult = movie.adult,
            backdrop_path = movie.backdrop_path,
            genre_ids = movie.genre_ids,
            id = movie.id,
            original_language = movie.original_language,
            original_title = movie.original_title,
            overview = movie.overview,
            poster_path = movie.poster_path,
            release_date = movie.release_date,
            title = movie.title,
            watched = false // Defaulting to 'false' when saving initially
        )
    }
}