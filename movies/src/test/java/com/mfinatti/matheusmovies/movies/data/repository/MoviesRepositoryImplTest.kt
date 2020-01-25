package com.mfinatti.matheusmovies.movies.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.mfinatti.matheusmovies.movies.data.local.MoviesDao
import com.mfinatti.matheusmovies.movies.data.remote.MoviesApi
import com.mfinatti.matheusmovies.movies.injection.IO_SCHEDULER
import common.MockPagedDataSource
import common.createDiscoverResponseDataModel
import common.createMovie
import common.createMovieDetailResponseDataModel
import common.createMovieOverviewList
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.shouldEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

class MoviesRepositoryImplTest : KoinTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockApi = mockk<MoviesApi> {
        every { getMovie(any()) } answers {
            Single.just(createMovieDetailResponseDataModel(firstArg()))
        }

        every { getPopularMovies(any()) } answers {
            Single.just(createDiscoverResponseDataModel(firstArg(), 1))
        }
    }

    private val mockDao = mockk<MoviesDao>(relaxed = true)

    private val disposeBag = CompositeDisposable()

    private val pagedListConfig = PagedList.Config.Builder().setPageSize(1).build()

    private val overviews = createMovieOverviewList(1)

    private val repository = MoviesRepositoryImpl(
        mockApi,
        mockDao,
        disposeBag,
        pagedListConfig
    )

    @Before
    fun setup() {
        startKoin {}
        loadKoinModules(
            module {
                single(named(IO_SCHEDULER)) { Schedulers.trampoline() }
            }
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `when getting movie details, given it's cached, then it should return from database`() {
        // Given
        every { mockDao.getMovie(any()) } answers { Single.just(createMovie(firstArg())) }

        // When
        val testObserver = repository.getMovieDetails(1).test()

        // Then
        val movie = testObserver.values()[0]
        movie shouldEqual createMovie(1)

        verify { mockDao.getMovie(1) }
        verify(exactly = 0) { mockApi.getMovie(1) }
    }

    @Test
    fun `when getting movie details, given it's not cached, it should get from api and save to db`() {
        // Given
        every { mockDao.getMovie(1) } answers { Single.error(Exception()) }

        // When
        val testObserver = repository.getMovieDetails(1).test()

        // Then
        val movie = testObserver.values()[0]
        movie shouldEqual createMovie(1)

        verify { mockApi.getMovie(1) }
        verify { mockDao.insertMovie(movie) }
    }

    @Test
    fun `when getting popular movies, given there is cache, it should return an observable with the movies`() {
        // Given
        every { mockDao.getOverviews() } returns MockPagedDataSource(overviews)

        // When
        val testObserver = repository.getPopularMovies(1).test()

        // Then
        val observed = testObserver.values()[0]
        observed shouldEqual overviews

        testObserver.dispose()
    }
}
