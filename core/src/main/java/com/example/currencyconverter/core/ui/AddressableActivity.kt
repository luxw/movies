package com.example.currencyconverter.core.ui

import android.content.Intent

/**
 * Defines an [android.app.Activity] that can be navigated to with an [Intent].
 */
interface AddressableActivity {

    /**
     * The activity's classname.
     * This is necessary due to activities existing in different app modules and therefore
     * the classpath not being accessible to the outside.
     */
    val className: String
}
