package com.example.sampleapp.movielist.api

import com.example.sampleapp.movielist.data.PopularMovies
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIInterface {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key : String) : Observable<PopularMovies>
}
