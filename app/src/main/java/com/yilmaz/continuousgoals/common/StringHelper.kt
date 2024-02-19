package com.yilmaz.continuousgoals.common

import java.util.Locale

class StringHelper {

    fun capitalize(string: String): String = string.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }

}