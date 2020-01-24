package com.mfinatti.matheusmovies.movies.presentation.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.usecases.GetDiscoverMoviesUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

internal class DiscoverViewModel(
    val router: DiscoverRouter,
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler,
    private val disposeBag: CompositeDisposable
) : ViewModel(), MovieClickListener {

    internal fun getMovies(): LiveData<DiscoverUiModel> {
        Log.i("Get movies")

        val liveData = MutableLiveData<DiscoverUiModel>()

        val disposable = getDiscoverMoviesUseCase.execute()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribeBy(
                onError = {
                    Log.d("Error! $it")
                    liveData.postValue(DiscoverUiModel.Error)
                },
                onNext = { movies ->
                    Log.d("movies ${movies}}")
                    liveData.postValue(DiscoverUiModel.Loaded(movies))
                }
            )

        disposeBag.add(disposable)

        return liveData
    }

    override fun onMovieClicked(movie: MovieOverview) {
        router.goToMovieDetail(movie)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}
