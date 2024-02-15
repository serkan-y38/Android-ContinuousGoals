package com.yilmaz.continuousgoals.presentation.secreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yilmaz.continuousgoals.presentation.navigation.SetUpNavigationGraph
import com.yilmaz.continuousgoals.presentation.ui.theme.ContinuousGoalsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ContinuousGoalsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetSystemBarsColor()
                    SetUpNavigationGraph(
                        navHostController = rememberNavController()
                    )
                }
            }
        }
    }
}

@Composable
fun SetSystemBarsColor() {
    rememberSystemUiController().also { systemUiController ->
        systemUiController.setSystemBarsColor(MaterialTheme.colorScheme.surface)
        if (!isSystemInDarkTheme())
            SideEffect { systemUiController.statusBarDarkContentEnabled = true }
        else
            SideEffect { systemUiController.statusBarDarkContentEnabled = false }
    }
}