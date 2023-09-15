package com.example.moviecrupapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecrupapp.API.API
import com.example.moviecrupapp.Adapters.RVAdapter
import com.example.moviecrupapp.Components.DComponent
import com.example.moviecrupapp.Components.DaggerDComponent
import com.example.moviecrupapp.Modals.RandomMovieResults
import com.example.moviecrupapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    val adapterForMovieList = RVAdapter()

    @Inject
    lateinit var API: API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movieRecylerView.apply {
            adapter = adapterForMovieList
            layoutManager = LinearLayoutManager(this.context)
        }

        val getMoviesCall = API.getMoviesRandom(
            includeAdult = true,
            includeVideo = false,
            language = "en-US",
            page = 1,
            sortBy = "popularity.desc"
        )

        getMoviesCall.enqueue(object : retrofit2.Callback<RandomMovieResults>{
            override fun onResponse(
                call: Call<RandomMovieResults>,
                response: Response<RandomMovieResults>
            ) {
                if (response.isSuccessful) {
                    val res = response.body()
                    if (res != null) {
                        Log.d("abcde", res.toString())
                        adapterForMovieList.updateList(res.results)
                    } else {
                        Log.d("abcdef", "Response is null")
                    }
                } else {
                    Log.d("abcd", response.toString())
                }
            }

            override fun onFailure(call: Call<RandomMovieResults?>, t: Throwable) {
                Log.d("abcddd", t.toString())
            }

        })


    }

    private fun initDependencies() {
        DaggerDComponent.builder().build().inject(this@MainActivity)
    }
}