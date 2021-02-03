package com.example.demoproject.utils

import android.content.Context
import android.util.DisplayMetrics

const val BASE_URL = "https://jsonplaceholder.typicode.com"

object RequestRoutes {
    const val post = "/posts"
}

// This should be written inside a helper but that's that (Programming Debt)
fun toPixels(dp: Float, context: Context?): Float {
    return dp * ((context?.resources
        ?.displayMetrics?.densityDpi?.toFloat()?:0f) / DisplayMetrics.DENSITY_DEFAULT)
}