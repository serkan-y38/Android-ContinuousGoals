package com.yilmaz.continuousgoals.data.repository

import com.yilmaz.continuousgoals.data.local.dao.GoalItemDao
import com.yilmaz.continuousgoals.domain.model.GoalItem
import com.yilmaz.continuousgoals.domain.repository.GoalItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoalItemRepositoryImpl @Inject constructor(
    private val dao: GoalItemDao
) : GoalItemRepository {

    override suspend fun updateGoalItem(model: GoalItem) {
        dao.updateGoalItem(model)
    }

    override fun getGoalItems(goalId: Int): Flow<List<GoalItem>> {
        return dao.getGoalItems(goalId)
    }

    override fun getInitialStateGoalItem(goalId: Int): Flow<List<GoalItem>> {
        return dao.getInitialStateGoalItem(goalId)
    }

    override suspend fun insertGoalItems(itemsSize: Int, goalId: Int) {
        dao.insertGoalItems(itemsSize, goalId)
    }

}