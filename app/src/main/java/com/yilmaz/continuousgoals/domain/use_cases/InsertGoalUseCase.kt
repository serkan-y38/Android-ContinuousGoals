package com.yilmaz.continuousgoals.domain.use_cases

import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.domain.repository.GoalRepository
import javax.inject.Inject

class InsertGoalUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    suspend operator fun invoke(model: Goal) {
        repository.insertGoal(model)
    }
}