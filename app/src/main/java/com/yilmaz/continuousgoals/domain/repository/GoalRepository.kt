package com.yilmaz.continuousgoals.domain.repository

import com.yilmaz.continuousgoals.domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    suspend fun insertGoal(model: Goal)

    suspend fun updateGoal(model: Goal)

    suspend fun deleteGoal(model: Goal)

    fun getAllGoals(): Flow<List<Goal>>

    fun search(query: String): Flow<List<Goal>>

}