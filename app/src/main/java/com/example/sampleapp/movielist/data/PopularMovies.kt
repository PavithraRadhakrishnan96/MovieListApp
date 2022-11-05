package com.example.sampleapp.movielist.data

import com.example.sampleapp.movielist.data.MoviesList

data class PopularMovies(val page: Int, val results: List<MoviesList>, val total_pages: Int, val total_results: Int)
