package com.yilmaz.continuousgoals.domain.use_cases.goal

import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGoalsUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    operator fun invoke(): Flow<List<Goal>> = repository.getAllGoals()
}