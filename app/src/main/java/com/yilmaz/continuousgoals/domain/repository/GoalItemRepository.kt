package com.yilmaz.continuousgoals.domain.repository

import com.yilmaz.continuousgoals.domain.model.GoalItem
import kotlinx.coroutines.flow.Flow

interface GoalItemRepository {

    suspend fun updateGoalItem(model: GoalItem)

    fun getGoalItems(goalId: Int): Flow<List<GoalItem>>

    suspend fun insertGoalItems(itemsSize: Int, goalId: Int)
}