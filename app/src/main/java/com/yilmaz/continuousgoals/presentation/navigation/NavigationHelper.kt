package com.yilmaz.continuousgoals.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.presentation.secreens.goal_detail.GoalDetailScreen
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
        composable(
            route = NavigationGraph.GoalDetailScreen.route
        ) {
            val goal = navHostController.previousBackStackEntry?.savedStateHandle?.get<Goal>(
                NavigationHelper.GOAL_MODEL_KEY
            )
            goal?.let { it1 -> GoalDetailScreen(navHostController, it1) }
        }
    }
}

object NavigationHelper {
    const val GOAL_MODEL_KEY = "goal"
}