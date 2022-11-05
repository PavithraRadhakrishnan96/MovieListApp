package com.example.sampleapp.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sampleapp.databinding.ActivityMovieListBinding
import com.example.sampleapp.movielist.viewmodel.MovieViewModel

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieListBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.observeMovieLiveData().observe(this, { movieList ->
            movieAdapter.setMovieList(movieList)
            binding.pbLoad.visibility= View.INVISIBLE

        })
        viewModel.errorMessage.observe(this, { errorMessage ->
            if (errorMessage != null) showError(errorMessage)
        })


    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
        binding.pbLoad.visibility= View.INVISIBLE


    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieListAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = movieAdapter
        }
    }

}