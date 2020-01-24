package com.mfinatti.matheusmovies.movies.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.mfinatti.matheusmovies.core.log.Log
import com.mfinatti.matheusmovies.movies.databinding.FragmentMoviedetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class MovieDetailFragment : Fragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieDetailViewModel by viewModel {
        parametersOf(args.movieId)
    }

    private lateinit var binding: FragmentMoviedetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviedetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated")

        viewModel.getMovieDetails().observe(viewLifecycleOwner, Observer { movie ->
            binding.movie = movie
            binding.executePendingBindings()
        })
    }
}
