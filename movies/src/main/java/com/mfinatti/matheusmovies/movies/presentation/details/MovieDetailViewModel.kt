package com.mfinatti.matheusmovies.movies.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.usecases.GetMovieDetailUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

internal class MovieDetailViewModel(
    private val movieId: Int,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModel() {

    private val disposeBag = CompositeDisposable()

    fun getMovieDetails(): LiveData<Movie> {
        val liveData = MutableLiveData<Movie>()

        getMovieDetailUseCase.execute(movieId)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribeBy(
                onNext = { movie ->
                    Log.d("movie: $movie")
                    liveData.postValue(movie)
                },
                onError = { error ->
                    Log.e("Error, $error")
                }
            ).addTo(disposeBag)

        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}
