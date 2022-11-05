package com.example.sampleapp.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp.databinding.MovieLayoutBinding
import com.example.sampleapp.movielist.data.MoviesList

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private var movieList = ArrayList<MoviesList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
            .into(holder.binding.movieImage)
        holder.binding.movieName.text = movieList[position].title    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieList(movieList : List<MoviesList>){
        this.movieList = movieList as ArrayList<MoviesList>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}