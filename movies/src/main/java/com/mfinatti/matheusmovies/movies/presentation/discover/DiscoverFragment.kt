package com.mfinatti.matheusmovies.movies.presentation.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mfinatti.matheusmovies.core.injection.coreDataModule
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.R
import com.mfinatti.matheusmovies.movies.injection.moviesDataModule
import com.mfinatti.matheusmovies.movies.injection.moviesModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.error.DefinitionOverrideException

/**
 * Fragment that serves as an entry point to the movies module of the application.
 * It shows the a list of movies.
 */
class DiscoverFragment : Fragment() {

    private val viewModel: DiscoverViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")

        // Since this is the first screen of the feature module, we need to load the injection
        // modules we're going to use.
        try {
            loadKoinModules(listOf(moviesDataModule, moviesModule))
        } catch (e: DefinitionOverrideException) {
            Log.d("module already loaded ${e.message}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_discover, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies()
    }
}
