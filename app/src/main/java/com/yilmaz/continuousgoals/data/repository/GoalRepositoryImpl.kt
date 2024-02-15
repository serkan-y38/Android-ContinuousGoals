package com.yilmaz.continuousgoals.data.repository

import com.yilmaz.continuousgoals.data.local.dao.GoalDao
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val dao: GoalDao
) : GoalRepository {

    override suspend fun insertGoal(model: Goal) = dao.insertGoal(model)

    override suspend fun updateGoal(model: Goal) = dao.updateGoal(model)

    override suspend fun deleteGoal(model: Goal) = dao.deleteGoal(model)

    override fun getAllGoals(): Flow<List<Goal>> = dao.getAllGoals()

    override fun search(query: String): Flow<List<Goal>> = dao.search(query)

}