package com.example.moviecrupapp.Adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecrupapp.Modals.Genre
import com.example.moviecrupapp.Modals.Genres
import com.example.moviecrupapp.Modals.Result
import com.example.moviecrupapp.R
import com.example.moviecrupapp.SinglePageActivity
import com.example.moviecrupapp.databinding.MovieCardBinding

class RVAdapter : RecyclerView.Adapter<MovieCardViewHolder>() {

    private val movieList: MutableList<Result> = mutableListOf()
    private val genreList: MutableList<Genre> = mutableListOf()
    private val savedMovieIds: MutableList<Int> = mutableListOf()

    private lateinit var layoutHandlers: LayoutHandlers

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val binding = MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieCardViewHolder(binding, layoutHandlers)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.onBind(
            movieList[position],
            returnGenreNames(movieList[position]),
            savedMovieIds.contains(movieList[position].id)
        )
    }

    fun returnGenreNames(movie: Result): List<String> {
        return movie.genre_ids.mapNotNull { genreId ->
            genreList.find { it.id == genreId }?.name
        }
    }

    fun updateGenres(genres: List<Genre>) {
        genreList.clear()
        genreList.addAll(genres)
        notifyDataSetChanged()
    }

    fun setLayoutHandlers(layoutHandler: LayoutHandlers) {
        this.layoutHandlers = layoutHandler
    }

    fun updateList(movies: List<Result>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun updateCheckedItems(ids: List<Int>) {
        val newSavedIdsSet = ids.toSet()
        val currentSavedIdsSet = savedMovieIds.toSet()

        for ((index, movie) in movieList.withIndex()) {
            val movieId = movie.id
            val isNowSaved = newSavedIdsSet.contains(movieId)
            val wasSaved = currentSavedIdsSet.contains(movieId)

            if (isNowSaved != wasSaved) {
                notifyItemChanged(index)
            }
        }

        savedMovieIds.clear()
        savedMovieIds.addAll(newSavedIdsSet)
    }
}

class MovieCardViewHolder(
    val movieCardBinding: MovieCardBinding,
    val layoutHandler: LayoutHandlers
) : RecyclerView.ViewHolder(movieCardBinding.root) {

    fun onBind(movie: Result, genreNames: List<String>, isChecked: Boolean) {
        with(movieCardBinding) {
            // 1. Set movie title
            title.text = if (movie.title.length >= 24) "${movie.title.take(24)}..." else movie.title

            // 2. Load image with Glide
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Glide.with(root.context)
                .load(imageUrl)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(movieListImage)

            // 3. Set other movie details
            des.text = genreNames.joinToString(", ")
            releaseDate.text = movie.release_date

            // 4. Set card click listener
            card.setOnClickListener {
                val intent = Intent(root.context, SinglePageActivity::class.java).apply {
                    putExtra("singleMovie", movie)
                    putExtra("genres", genreNames.joinToString(", "))
                }
                root.context.startActivity(intent)
            }

            // 5. Set checkbox image and click listener based on `isChecked`
            setCheckboxStateAndListener(isChecked, movie)
        }
    }

    private fun MovieCardBinding.setCheckboxStateAndListener(isChecked: Boolean, movie: Result) {
        // First, remove all existing click listeners to avoid stacking them
        checkBox.setOnClickListener(null)

        if (isChecked) {
            checkBox.setImageResource(R.drawable.baseline_remove_red_eye_24)
            checkBox.setOnClickListener {
                Log.d("CHECKBOX_CLICK", "Checkbox clicked!")
                Log.d("CHECKBOX_CLICK", "Removing")
                layoutHandler.onRemoveButtonClick(movie.id)
            }
        } else {
            checkBox.setImageResource(R.drawable.baseline_add_18)
            checkBox.setOnClickListener {
                Log.d("CHECKBOX_CLICK", "Checkbox clicked!")
                Log.d("CHECKBOX_CLICK", "Adding")
                layoutHandler.onAddButtonClick(layoutHandler.retrofitMovieToRoomMovieConverter(movie))

            }
        }
    }
}

//.thumbnail(Glide.with(binding.root.context).load(previewUrl))


