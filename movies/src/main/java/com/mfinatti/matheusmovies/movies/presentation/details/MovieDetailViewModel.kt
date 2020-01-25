package com.mfinatti.matheusmovies.movies.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.domain.model.extensions.toUiModel
import com.mfinatti.matheusmovies.movies.domain.usecases.GetMovieDetailUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * ViewModel for the movie detail screen.
 *
 * @param movieId id of the movie to be displayed.
 * @param getMovieDetailUseCase use case for loading movie details.
 * @param ioScheduler RxJava scheduler to run IO operations.
 * @param mainScheduler the scheduler to observe on android main thread.
 */
internal class MovieDetailViewModel(
    private val movieId: Int,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler
) : ViewModel() {

    private val disposeBag = CompositeDisposable()

    /**
     * Gets the details of the movie.
     *
     * @return a live data to a [MovieUiModel] which is a simplified version of the domain entity.
     */
    fun getMovieDetails(): LiveData<MovieUiModel> {
        val liveData = MutableLiveData<MovieUiModel>()

        getMovieDetailUseCase.execute(movieId)
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribeBy(
                onNext = { movie ->
                    Log.d("movie: $movie")
                    liveData.postValue(movie.toUiModel())
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
