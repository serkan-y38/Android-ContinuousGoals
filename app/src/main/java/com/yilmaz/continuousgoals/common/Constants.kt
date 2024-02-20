package com.yilmaz.continuousgoals.common

import android.graphics.Color

object Constants {

    const val APP_DB_NAME = "goal_database"

    val colors = listOf(
        0xFFF44336,
        0xFFE91E63,
        0xFF9C27B0,
        0xFF673AB7,
        0xFF3F51B5,
        0xFF2196F3,
        0xFF03A9F4,
        0xFF00BCD4,
        0xFF009688,
        0xFF4CAF50,
        0xFF8BC34A,
        0xFFCDDC39,
        0xFFFFEB3B,
        0xFFFFC107,
        0xFFFF9800,
        0xFFFF5722
    )

    val colorsForPieChart = listOf(
        Color.parseColor("#9CCC65"),
        Color.parseColor("#EF5350"),
        Color.parseColor("#29B6F6")
    )

    const val THEME_PREFERENCES_KEY = "theme"
    const val DATA_STORE_PREFERENCES_NAME = "settings"
    val themes = listOf("System setting", "Dark mode", "Light mode")

}