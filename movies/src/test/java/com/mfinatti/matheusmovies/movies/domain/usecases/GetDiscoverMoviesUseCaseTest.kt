package com.mfinatti.matheusmovies.movies.domain.usecases

import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import common.createMovieOverviewList
import common.mockPagedList
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.amshove.kluent.shouldEqual
import org.junit.Test

class GetDiscoverMoviesUseCaseTest {

    private val movieList = createMovieOverviewList(1)

    private val mockRepository = mockk<MoviesRepository> {
        every { getPopularMovies(any()) } returns Observable.just(
            mockPagedList(movieList)
        )
    }

    private val useCase = GetDiscoverMoviesUseCase(mockRepository)

    @Test
    fun `when executing the use case, then it should return an observable to a list of movies`() {
        // Given

        // When
        val testObserver = useCase.execute().test()

        // Then
        val pagedList = testObserver.values()[0]
        pagedList[0] shouldEqual movieList[0]

        testObserver.dispose()
    }
}
