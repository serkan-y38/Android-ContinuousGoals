package com.yilmaz.continuousgoals.presentation.secreens.goal_detail

import com.yilmaz.continuousgoals.domain.model.GoalItem

data class GoalItemState(
    val goalItems: List<GoalItem> = emptyList(),
    var initialStateGoalItem: List<GoalItem> = emptyList()
)
