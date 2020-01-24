package com.mfinatti.matheusmovies.movies.presentation.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.data.repository.LoadingState
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import com.mfinatti.matheusmovies.movies.domain.usecases.GetDiscoverMoviesUseCase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

internal class DiscoverViewModel(
    val router: DiscoverRouter,
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModel(), MovieClickListener {

    private val disposeBag = CompositeDisposable()

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
                onNext = { (movies, loadingState) ->
                    Log.d("movies ${movies.map { "${it?.title} - ${it?.popularity}" }}")
                    liveData.postValue(DiscoverUiModel.Loaded(movies))

                    when (loadingState) {
                        LoadingState.Loading -> {} // Do nothing, no loading ui state is programmed.
                        LoadingState.Success -> {} // Do nothing, nothing special for success case.
                        is LoadingState.Error -> liveData.postValue(DiscoverUiModel.Error)
                    }
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