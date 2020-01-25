package common

import com.mfinatti.matheusmovies.movies.data.remote.model.DiscoverResponseDataModel
import com.mfinatti.matheusmovies.movies.data.remote.model.MovieDetailResponseDataModel
import com.mfinatti.matheusmovies.movies.data.remote.model.MovieOverviewDataModel
import com.mfinatti.matheusmovies.movies.domain.model.Movie
import com.mfinatti.matheusmovies.movies.domain.model.MovieOverview
import java.time.Instant
import java.util.Date

internal fun createMovieOverviewList(size: Int) =
    (1..size).map { i ->
        MovieOverview(
            id = i,
            title = "Test",
            poster = "",
            overview = "overview",
            releaseDate = Date.from(Instant.EPOCH),
            voteAverage = 0.0,
            originalLanguage = "en",
            popularity = 0.0
        )
    }

internal fun createMovie(id: Int) =
    Movie(
        id = id,
        title = "Test",
        poster = "",
        backdrop = "",
        originalLanguage = "en",
        originalTitle = "Test",
        genres = listOf(),
        homepage = "",
        status = "",
        overview = "overview",
        releaseDate = Date.from(Instant.EPOCH),
        voteAverage = 0.0,
        tagline = "",
        runtime = 0
    )

internal fun createMovieDetailResponseDataModel(id: Int) =
    MovieDetailResponseDataModel(
        id = id,
        title = "Test",
        voteCount = 0,
        posterPath = "",
        popularity = 0.0,
        adult = false,
        backdropPath = "",
        originalLanguage = "en",
        originalTitle = "Test",
        voteAverage = 0.0,
        overview = "overview",
        releaseDate = Date.from(Instant.EPOCH),
        homepage = "",
        budget = 0,
        revenue = 0,
        status = "",
        tagline = "",
        runtime = 0,
        genres = listOf()
    )

internal fun createDiscoverResponseDataModel(page: Int, results: Int) =
    DiscoverResponseDataModel(
        page = page,
        totalPages = 1,
        totalResults = 1,
        results = (1..results).map { createMovieOverviewDataModel(it) }
    )

internal fun createMovieOverviewDataModel(id: Int) =
    MovieOverviewDataModel(
        id = id,
        title = "Test",
        originalTitle = "Test",
        overview = "overview",
        voteCount = 0,
        voteAverage = 0.0,
        adult = false,
        posterPath = "",
        backdropPath = "",
        genreIds = listOf(),
        releaseDate = Date.from(Instant.EPOCH),
        popularity = 0.0,
        originalLanguage = "en"
    )
