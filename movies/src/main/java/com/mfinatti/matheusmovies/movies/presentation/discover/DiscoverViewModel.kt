package com.mfinatti.matheusmovies.movies.presentation.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.usecases.GetDiscoverMoviesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

internal class DiscoverViewModel(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
) : ViewModel() {

    private val disposeBag = CompositeDisposable()

    internal fun getMovies(): LiveData<List<MovieOverview>> {
        val liveData = MutableLiveData<List<MovieOverview>>()

        val disposable = getDiscoverMoviesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    Log.d("Error! $it")
                },
                onNext = { movies ->
                    Log.d("movies $movies")
                    liveData.postValue(movies)
                }
            )

        disposeBag.add(disposable)

        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}