package com.example.moviecrupapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecrupapp.Modals.Result
import com.example.moviecrupapp.R
import com.example.moviecrupapp.databinding.MovieCardBinding

class RVAdapter : RecyclerView.Adapter<MovieCardViewHolder>() {

    private val movieList: MutableList<Result> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        val binding = MovieCardBinding.inflate(LayoutInflater.from(parent.context))
        return MovieCardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    fun updateList(newList : List<Result>)
    {
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}

class MovieCardViewHolder(val movieCardBinding: MovieCardBinding) : RecyclerView.ViewHolder(movieCardBinding.root) {

    fun onBind(movie : Result) {
        with(movieCardBinding) {
            title.text = movie.title
//            Glide.with(binding.root.context)
//                .load(url)
//                .thumbnail(Glide.with(binding.root.context).load(previewUrl))
//                .centerCrop()
//                .into(imageContainer)
        }
    }
}