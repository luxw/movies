@file:Suppress("MatchingDeclarationName")
package com.mfinatti.matheusmovies.core.ui

import android.content.Intent

private const val PACKAGE_NAME = "com.example.currencyconverter"

/**
 * Generates an [Intent] to an [AddressableActivity].
 */
fun getIntentTo(addressableActivity: AddressableActivity) =
    Intent(Intent.ACTION_VIEW).setClassName(
        PACKAGE_NAME,
        addressableActivity.className
    )

/**
 * The rates feature module activity.
 */
object Rates : AddressableActivity {

    override val className = "$PACKAGE_NAME.rates.ui.RatesActivity"
}
