package com.example.moviecrupapp.viewModals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.moviecrupapp.Modals.Genre
import com.example.moviecrupapp.Modals.Genres
import com.example.moviecrupapp.Modals.RandomMovieResults
import com.example.moviecrupapp.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class mainViewModal @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    val movieList = MutableLiveData<RandomMovieResults>()
    val genreList = MutableLiveData<Genres>()

    fun setData(movies: RandomMovieResults, genres: Genres){
        movieList.postValue(movies)
        genreList.postValue(genres)
    }
    init {
        viewModelScope.launch(Dispatchers.IO){
            val movies = async {
                movieRepository.getMoviesRandom()
            }
            val genres = async {
                movieRepository.getGenreList()
            }
            setData(movies.await(), genres.await())
        }
    }

}