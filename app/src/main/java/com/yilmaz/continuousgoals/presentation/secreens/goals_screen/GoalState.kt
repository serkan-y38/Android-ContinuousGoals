package com.yilmaz.continuousgoals.presentation.secreens.goals_screen

import com.yilmaz.continuousgoals.domain.model.Goal

data class GoalState(
    var goals: List<Goal> = emptyList(),
    var searchedGoals: List<Goal> = emptyList(),
)
