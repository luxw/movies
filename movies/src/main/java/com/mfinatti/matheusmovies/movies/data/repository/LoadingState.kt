package com.mfinatti.matheusmovies.movies.data.repository

/**
 * Represents data loading states.
 */
sealed class LoadingState {

    /**
     * Loading data.
     */
    object Loading : LoadingState()

    /**
     * Loaded data successfully.
     */
    object Success : LoadingState()

    /**
     * Error while loading data.
     */
    class Error(val error: Throwable) : LoadingState()
}
