package com.mfinatti.matheusmovies.movies.presentation.discover

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mfinatti.matheusmovies.movies.domain.usecases.GetDiscoverMoviesUseCase
import common.createMovieOverviewList
import common.mockPagedList
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Rule
import org.junit.Test

class DiscoverViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val movieList = createMovieOverviewList(4)

    private val mockGetDiscoverMoviesUseCase = mockk<GetDiscoverMoviesUseCase> {
        every { execute() } returns Observable.just(
            mockPagedList(movieList)
        )
    }

    private val mockRouter = mockk<DiscoverRouter>(relaxed = true)

    private val viewModel = DiscoverViewModel(
        mockRouter,
        mockGetDiscoverMoviesUseCase,
        Schedulers.trampoline(),
        Schedulers.trampoline(),
        CompositeDisposable()
    )

    @Test
    fun `when getting movies, then a list of movies should be delivered`() {
        // Given
        val mockObserver = mockk<Observer<DiscoverUiModel>>(relaxed = true)

        // When
        viewModel.getMovies().observeForever(mockObserver)

        // Then
        val modelSlot = slot<DiscoverUiModel>()
        verify {
            mockObserver.onChanged(capture(modelSlot))
        }

        modelSlot.captured shouldBeInstanceOf DiscoverUiModel.Loaded::class
        (modelSlot.captured as DiscoverUiModel.Loaded).movies[0] shouldEqual movieList[0]
    }

    @Test
    fun `when clicking on a movie, it should navigate to detail screen`() {
        // Given

        // When
        viewModel.onMovieClicked(movieList[0])

        // Then
        verify { mockRouter.goToMovieDetail(movieList[0]) }
    }
}
