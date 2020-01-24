package com.mfinatti.matheusmovies.movies.presentation.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.core.view.extensions.showErrorSnackBar
import com.mfinatti.matheusmovies.movies.R
import com.mfinatti.matheusmovies.movies.injection.injectFeatures
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment that serves as an entry point to the movies module of the application.
 * It shows the a list of movies.
 */
class DiscoverFragment : Fragment() {

    private val viewModel: DiscoverViewModel by viewModel()

    private val adapter by lazy {
        DiscoverAdapter(viewModel as MovieClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate")

        injectFeatures()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_discover, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated")

        // Change the toolbar text
        view.findViewById<Toolbar>(R.id.toolbar).title = getString(R.string.feature_name)

        // Set the router navController to enable navigation.
        viewModel.router.navController = findNavController()

        view.findViewById<RecyclerView>(R.id.movies_listView).adapter = adapter

        viewModel.getMovies().observe(viewLifecycleOwner, Observer { uiModel ->
            when (uiModel) {
                is DiscoverUiModel.Loaded -> adapter.submitList(uiModel.movies)
                DiscoverUiModel.Error -> showErrorSnackBar(R.string.error_loading_overview, view)
            }
        })
    }
}
