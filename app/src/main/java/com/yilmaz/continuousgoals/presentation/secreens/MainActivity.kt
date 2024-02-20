package com.yilmaz.continuousgoals.presentation.secreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.yilmaz.continuousgoals.common.Constants
import com.yilmaz.continuousgoals.presentation.navigation.SetUpNavigationGraph
import com.yilmaz.continuousgoals.presentation.secreens.settings.SetSystemBarsColor
import com.yilmaz.continuousgoals.presentation.ui.theme.ContinuousGoalsTheme
import com.yilmaz.continuousgoals.presentation.utils.DataStoreUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val theme = DataStoreUtils(applicationContext).getTheme()
                .collectAsState(initial = Constants.themes[0])

            ContinuousGoalsTheme(
                darkTheme = when (theme.value) {
                    Constants.themes[0] -> isSystemInDarkTheme()
                    Constants.themes[1] -> true
                    else -> false
                }
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetSystemBarsColor(theme.value)
                    SetUpNavigationGraph(navHostController = rememberNavController())
                }
            }
        }
    }
}