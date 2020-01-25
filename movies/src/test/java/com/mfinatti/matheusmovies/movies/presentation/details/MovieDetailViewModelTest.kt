package com.mfinatti.matheusmovies.movies.presentation.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mfinatti.matheusmovies.movies.domain.model.extensions.toUiModel
import com.mfinatti.matheusmovies.movies.domain.usecases.GetMovieDetailUseCase
import common.createMovie
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.shouldEqual
import org.junit.Rule
import org.junit.Test

class MovieDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockGetMovieDetailUseCase = mockk<GetMovieDetailUseCase> {
        every { execute(any()) } answers {
            Observable.just(createMovie(firstArg()))
        }
    }

    private val id = 1

    private val viewModel = MovieDetailViewModel(
        id,
        mockGetMovieDetailUseCase,
        Schedulers.trampoline(),
        Schedulers.trampoline()
    )

    @Test
    fun `when getting a movie detail, then it should deliver a movie`() {
        // Given
        val movie = createMovie(id)
        val mockObserver = mockk<Observer<MovieUiModel>>(relaxed = true)

        // When
        viewModel.getMovieDetails().observeForever(mockObserver)

        // Then
        val uiModelSlot = slot<MovieUiModel>()
        verify { mockObserver.onChanged(capture(uiModelSlot)) }

        movie.toUiModel() shouldEqual uiModelSlot.captured
    }
}
