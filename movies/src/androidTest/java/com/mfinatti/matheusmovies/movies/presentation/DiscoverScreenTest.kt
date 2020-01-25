package com.mfinatti.matheusmovies.movies.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mfinatti.matheusmovies.movies.presentation.discover.DiscoverFragment
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiscoverScreenTest {

    @Test
    fun test_movies_are_displayed_on_screen() {
        // Mock the result of database call

        // Mock navController
        val mockNavController = mockk<NavController>(relaxed = true)

        // Create the discover fragment
        val discoverScenario = launchFragmentInContainer<DiscoverFragment>()

        // Set the nav controller
        discoverScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // What now?
    }
}