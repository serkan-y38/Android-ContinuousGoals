package com.yilmaz.continuousgoals.domain.use_cases.goal_item

import com.yilmaz.continuousgoals.domain.model.GoalItem
import com.yilmaz.continuousgoals.domain.repository.GoalItemRepository
import javax.inject.Inject

class UpdateGoalItemUseCase @Inject constructor(
    private val goalItemRepository: GoalItemRepository
) {
    suspend operator fun invoke(model: GoalItem) {
        goalItemRepository.updateGoalItem(model)
    }
}