package com.yilmaz.continuousgoals.presentation.secreens.goal_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.presentation.secreens.goal_detail.view_model.GoalItemViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@Composable
fun GoalDetailScreen(
    navController: NavController,
    goal: Goal,
    goalItemViewModel: GoalItemViewModel = hiltViewModel()
) {

    fun getDifferenceInDays(dateStr1: String, dateStr2: String): Long {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return TimeUnit.MILLISECONDS.toDays(
            (dateFormat.parse(dateStr1) as Date).time - (dateFormat.parse(dateStr2) as Date).time
        )
    }

    LaunchedEffect(key1 = goal) {
        if (!goal.isGoalItemsCreated)
            goalItemViewModel.insertGoalItems(
                getDifferenceInDays(goal.endDate, goal.startDate).toInt(),
                goal.goalId!!
            )
    }

}