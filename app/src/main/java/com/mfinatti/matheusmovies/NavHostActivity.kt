/**
 * Copyright © 2019 Liebherr-Hausgeräte GmbH. All rights reserved.
 */

package com.mfinatti.matheusmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Main activity works as a holder of the navigation host view, which swaps between the different
 * fragments we have in the application.
 */
class NavHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navhost)

        supportActionBar?.hide()
    }
}
