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

/**
 * ViewModel for the discover movies screen.
 *
 * @param router the router to enable navigation.
 * @param getDiscoverMoviesUseCase use case for getting movies.
 * @param ioScheduler a RxJava scheduler for IO operations.
 * @param mainScheduler the scheduler to observe on android main thread.
 * @param disposeBag the dispose bag to dispose subscriptions.
 */
internal class DiscoverViewModel(
    val router: DiscoverRouter,
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler,
    private val disposeBag: CompositeDisposable
) : ViewModel(), MovieClickListener {

    /**
     * Gets a live data to a list of paged movies.
     *
     * @return a live data to [DiscoverUiModel], where [DiscoverUiModel.Loaded] means that
     * the list was successfully loaded and [DiscoverUiModel.Error] if not.
     * [DiscoverUiModel.Loaded] has the loaded list as parameter.
     */
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
                    Log.d("movies $movies}")
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
