package com.yilmaz.continuousgoals.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.GoalsScreen

@Composable
fun SetUpNavigationGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationGraph.GoalsScreen.route
    ) {
        composable(
            route = NavigationGraph.GoalsScreen.route
        ) {
            GoalsScreen(navHostController)
        }
    }
}