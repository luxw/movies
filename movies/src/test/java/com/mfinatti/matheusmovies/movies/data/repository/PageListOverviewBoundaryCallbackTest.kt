package com.mfinatti.matheusmovies.movies.data.repository

import com.mfinatti.matheusmovies.movies.data.local.MoviesDao
import com.mfinatti.matheusmovies.movies.data.remote.MoviesApi
import com.mfinatti.matheusmovies.movies.injection.IO_SCHEDULER
import common.createDiscoverResponseDataModel
import common.createMovieOverviewList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

class PageListOverviewBoundaryCallbackTest : KoinTest {

    private val mockApi = mockk<MoviesApi>(relaxed = true) {
        every { getPopularMovies(any()) } answers {
            Single.just(createDiscoverResponseDataModel(firstArg(), 1))
        }
    }

    private val mockDao = mockk<MoviesDao>(relaxed = true)

    private val disposeBag = CompositeDisposable()

    private val item = createMovieOverviewList(1)[0]

    private lateinit var boundaryCallback: PageListOverviewBoundaryCallback

    @Before
    fun setup() {
        startKoin {}
        loadKoinModules(
            module {
                single(named(IO_SCHEDULER)) { Schedulers.trampoline() }
            }
        )

        boundaryCallback = PageListOverviewBoundaryCallback(
            mockApi,
            mockDao,
            disposeBag,
            1
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when item at end is loaded, then it should fetch and store movies`() {
        // Given

        // When
        boundaryCallback.onItemAtEndLoaded(item)

        // Then
        verify { mockApi.getPopularMovies(1) }
        verify { mockDao.insertOverviews(listOf(item)) }
    }

    @Test
    fun `when zero items are loaded, then it should fetch and store movies`() {
        // Given

        // When
        boundaryCallback.onZeroItemsLoaded()

        // Then
        verify { mockApi.getPopularMovies(1) }
        verify { mockDao.insertOverviews(listOf(item)) }
    }
}
