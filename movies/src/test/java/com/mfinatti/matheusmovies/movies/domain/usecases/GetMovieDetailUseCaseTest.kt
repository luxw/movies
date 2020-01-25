package com.mfinatti.matheusmovies.movies.domain.usecases

import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import common.createMovie
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.amshove.kluent.shouldEqual
import org.junit.Test

class GetMovieDetailUseCaseTest {

    private val mockRepository = mockk<MoviesRepository> {
        every { getMovieDetails(any()) } answers {
            Observable.just(createMovie(firstArg()))
        }
    }

    private val useCase = GetMovieDetailUseCase(mockRepository)

    @Test
    fun `when executing, it should return an observable to a movie`() {
        // Given

        // When
        val testObserver = useCase.execute(1).test()

        // Then
        val movie = testObserver.values()[0]
        movie shouldEqual createMovie(1)

        testObserver.dispose()
    }
}
