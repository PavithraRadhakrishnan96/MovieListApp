package com.example.sampleapp.movielist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sampleapp.movielist.viewmodel.BaseViewModel
import com.example.sampleapp.movielist.api.MovieAPIInterface
import com.example.sampleapp.movielist.data.MoviesList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieViewModel : BaseViewModel() {

    @Inject
    lateinit var homeApi: MovieAPIInterface
    private lateinit var disposable: Disposable
    private var movieLiveData = MutableLiveData<List<MoviesList>>()
    var errorMessage: MutableLiveData<String> = MutableLiveData()

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        disposable = homeApi.getPopularMovies("ed0bb6664e74c6c6a3f96506d1b6af83").subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    movieLiveData.value = result.results

                },
                {
                    errorMessage.value=it.toString()
                }

            )

    }
    fun observeMovieLiveData(): LiveData<List<MoviesList>> {
        return movieLiveData
    }



}