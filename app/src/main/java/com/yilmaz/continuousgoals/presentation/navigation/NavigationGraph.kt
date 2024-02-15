package com.yilmaz.continuousgoals.presentation.navigation

sealed class NavigationGraph(val route: String) {
    data object GoalsScreen : NavigationGraph(route = "goals_screen")
}