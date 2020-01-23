package com.mfinatti.matheusmovies.movies.injection

import com.mfinatti.matheusmovies.core.network.ApiKeyInterceptor
import com.mfinatti.matheusmovies.movies.BuildConfig
import com.mfinatti.matheusmovies.movies.data.repository.MoviesApi
import com.mfinatti.matheusmovies.movies.data.repository.MoviesRepositoryImpl
import com.mfinatti.matheusmovies.movies.domain.repository.MoviesRepository
import com.mfinatti.matheusmovies.movies.domain.usecases.GetDiscoverMoviesUseCase
import com.mfinatti.matheusmovies.movies.presentation.discover.DiscoverViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

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

    // Repository
    single<MoviesRepository> {
        MoviesRepositoryImpl(get())
    }
}

val moviesModule = module {

    factory { GetDiscoverMoviesUseCase(get()) }

    viewModel { DiscoverViewModel(get()) }
}

private val loadModules by lazy {
    loadKoinModules(listOf(moviesDataModule, moviesModule))
}

/**
* Enables injecting on this feature.
*/
fun injectFeatures() = loadModules

private const val ENDPOINT = "https://api.themoviedb.org/3/"