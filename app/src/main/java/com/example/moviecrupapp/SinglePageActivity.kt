package com.example.moviecrupapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.moviecrupapp.Adapters.LayoutHandlers
import com.example.moviecrupapp.Modals.Result
import com.example.moviecrupapp.databinding.ActivitySinglePageBinding
import com.example.moviecrupapp.roomDB.MovieDao
import com.example.moviecrupapp.roomDB.SavedMovies
import javax.inject.Inject

class SinglePageActivity : AppCompatActivity(){

    private lateinit var binding : ActivitySinglePageBinding

    @Inject
    lateinit var movieDao : MovieDao


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySinglePageBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUiFromIntent()

        with(binding){
            backButton.setOnClickListener{
                finish()
            }
        }
    }

    fun setUiFromIntent(){
        var thisMovie = intent.getParcelableExtra<Result>("singleMovie")
        with(this.binding) {
            Glide.with(this.root.context)
                .load("https://image.tmdb.org/t/p/w780" + thisMovie?.backdrop_path)
                .thumbnail(Glide.with(this.root.context).load(R.drawable.loader))
                .into(moviePoster)
            singlePageGenreText.text = intent.getStringExtra("genres")
            movieDescription.text = thisMovie?.overview
            addButton.setOnClickListener{

            }
        }
    }
}