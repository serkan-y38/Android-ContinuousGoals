package com.yilmaz.continuousgoals.presentation.navigation

sealed class NavigationGraph(val route: String) {
    data object GoalsScreen : NavigationGraph(route = "goals_screen")
    data object GoalDetailScreen : NavigationGraph(route = "goal_detail_screen")
    data object SettingsScreen : NavigationGraph(route = "settings_screen")
}