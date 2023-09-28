package com.example.moviecrupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecrupapp.API.API
import com.example.moviecrupapp.Adapters.LayoutHandlers
import com.example.moviecrupapp.Adapters.RVAdapter
import com.example.moviecrupapp.Components.DComponent
//import com.example.moviecrupapp.Components.DaggerDComponent
import com.example.moviecrupapp.Modals.Genres
import com.example.moviecrupapp.Modals.RandomMovieResults
import com.example.moviecrupapp.databinding.ActivityMainBinding
import com.example.moviecrupapp.roomDB.MovieDao
import com.example.moviecrupapp.roomDB.SavedMovies
import com.example.moviecrupapp.viewModals.mainViewModal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity(), LayoutHandlers {


    val adapterForMovieList = RVAdapter()

//    @Inject
//    lateinit var API: API

    @Inject
    lateinit var mainViewModal: mainViewModal

    @Inject
    lateinit var movieDao : MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("Main Activity", "Main Activity Created")

        super.onCreate(savedInstanceState)
        initDependencies()


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        actionBar?.hide()

        // Set up the LiveData observation
//        movieDao.getSavedMovieIds().observe(this, Observer {
//            adapterForMovieList.updateCheckedItems(it)
//        })


        adapterForMovieList.setLayoutHandlers(this@MainActivity)

        binding.movieRecylerView.apply {
            adapter = adapterForMovieList
            layoutManager = LinearLayoutManager(this.context)
        }

        mainViewModal.movieList.observe(this, Observer {
            adapterForMovieList.updateList(it.results)
        })
        mainViewModal.genreList.observe(this, Observer {
            adapterForMovieList.updateGenres(it.genres)
        })

//        lifecycleScope.launch(Dispatchers.IO) {
//            val genres = async {
//                API.getGenreList()
//            }
//            val movies = async {
//                API.getMoviesRandom(
//                    includeAdult = true,
//                    includeVideo = false,
//                    language = "en-US",
//                    page = 1,
//                    sortBy = "popularity.desc"
//                )
//            }
//            updateUI(genres = genres.await() , movies = movies.await())
//        }


//        getMoviesCall.enqueue(object : retrofit2.Callback<RandomMovieResults>{
//            override fun onResponse(
//                call: Call<RandomMovieResults>,
//                response: Response<RandomMovieResults>
//            ) {
//                if (response.isSuccessful) {
//                    val res = response.body()
//                    if (res != null) {
//                        Log.d("abcde", res.toString())
//                        adapterForMovieList.updateList(res.results)
//                    } else {
//                        Log.d("abcdef", "Response is null")
//                    }
//                } else {
//                    Log.d("abcd", response.toString())
//                }
//            }
//
//            override fun onFailure(call: Call<RandomMovieResults?>, t: Throwable) {
//                Log.d("abcddd", t.toString())
//            }
//
//        })


    }

    override fun onAddButtonClick(movie: SavedMovies) {
        Log.d("in main", "Adding")
        lifecycleScope.launch(Dispatchers.IO) {
            movieDao.insertMovie(movie)
        }
    }

    override fun onRemoveButtonClick(id: Int) {
        Log.d("in main", "Removing")
        lifecycleScope.launch(Dispatchers.IO) {
            movieDao.removeMovie(id)
        }
    }

//    fun updateUI(movies : RandomMovieResults?, genres: Genres?){
//        lifecycleScope.launch(Dispatchers.Main) {
//            if(movies != null && genres != null) {
//                Log.d("ABCDEFG3", movies.toString())
//                Log.d("ABCDEFG4", genres.toString())
//                adapterForMovieList.updateList(movies.results, genres.genres)
//            }
//        }
//    }

    private fun initDependencies() {
        (applicationContext as MyApplication).appComponent.inject(this@MainActivity)
//        DaggerDComponent.builder().build().inject(this@MainActivity)
    }
}