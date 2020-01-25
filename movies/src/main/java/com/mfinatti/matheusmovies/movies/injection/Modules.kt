package com.mfinatti.matheusmovies.movies.injection

import androidx.paging.PagedList
import androidx.room.Room
import com.mfinatti.matheusmovies.core.network.ApiKeyInterceptor
import com.mfinatti.matheusmovies.movies.BuildConfig
import com.mfinatti.matheusmovies.movies.data.local.MovieDatabase
import com.mfinatti.matheusmovies.movies.data.remote.MoviesApi
import com.mfinatti.matheusmovies.movies.data.repository.MoviesRepositoryImpl
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import com.mfinatti.matheusmovies.movies.domain.usecases.GetDiscoverMoviesUseCase
import com.mfinatti.matheusmovies.movies.domain.usecases.GetMovieDetailUseCase
import com.mfinatti.matheusmovies.movies.presentation.details.MovieDetailViewModel
import com.mfinatti.matheusmovies.movies.presentation.discover.DiscoverRouter
import com.mfinatti.matheusmovies.movies.presentation.discover.DiscoverViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Movies data module.
 * Contains dependencies for the data part of the Movies module. Such as: Room database, retrofit,
 * DAOs.
 */
val moviesDataModule = module {

    // Customised http client to this module
    factory(named("movies")) {
        get<OkHttpClient>().newBuilder()
            .addInterceptor(ApiKeyInterceptor(BuildConfig.API_KEY))
            .build()
    }

    // Retrofit instance for the movies API.
    single<MoviesApi> {
        Retrofit.Builder()
            .client(get(named("movies")))
            .baseUrl(ENDPOINT)
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .addConverterFactory(get<MoshiConverterFactory>())
            .build()
            .create(MoviesApi::class.java)
    }

    // Database
    single {
        Room.databaseBuilder(
            androidApplication(),
            MovieDatabase::class.java,
            "movies.db"
        ).build()
    }

    // DAO
    single { get<MovieDatabase>().moviesDao() }

    // Repository
    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get(), get(named(DISPOSE_BAG)), get())
    }

    // PagedList Config
    @Suppress("MagicNumber")
    single {
        val pageSize = 20
        val initialLoadSizeHint = 40

        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(initialLoadSizeHint)
            .setPageSize(pageSize)
            .build()
    }

    // IO Scheduler
    single(named(IO_SCHEDULER)) { Schedulers.io() }
}

/**
 * Movies module.
 * Contains dependencies on the business logic of the movies module.
 */
val moviesModule = module {

    single(named(DISPOSE_BAG)) { CompositeDisposable() }

    // Use cases
    factory { GetDiscoverMoviesUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }

    // Router
    factory { DiscoverRouter() }

    // Discover ViewModel
    viewModel {
        DiscoverViewModel(
            get(),
            get(),
            Schedulers.io(),
            AndroidSchedulers.mainThread(),
            get(named(DISPOSE_BAG))
        )
    }

    // Movie detail ViewModel
    viewModel { (movieId: Int) ->
        MovieDetailViewModel(movieId, get(), Schedulers.io(), AndroidSchedulers.mainThread())
    }
}

private val loadModules by lazy {
    loadKoinModules(listOf(moviesDataModule, moviesModule))
}

/**
* Enables injecting on this feature.
*/
fun injectFeatures() = loadModules

private const val ENDPOINT = "https://api.themoviedb.org/3/"

private const val DISPOSE_BAG = "discoverDisposable"

/**
 * IO Scheduler named injection.
 */
const val IO_SCHEDULER = "ioScheduler"
