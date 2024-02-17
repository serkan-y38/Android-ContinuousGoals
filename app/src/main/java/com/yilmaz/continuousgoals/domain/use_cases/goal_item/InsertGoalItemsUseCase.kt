package com.yilmaz.continuousgoals.domain.use_cases.goal_item

import com.yilmaz.continuousgoals.domain.repository.GoalItemRepository
import javax.inject.Inject

class InsertGoalItemsUseCase @Inject constructor(
    private val goalItemRepository: GoalItemRepository
) {
    suspend operator fun invoke(itemsSize: Int, goalId: Int) {
        goalItemRepository.insertGoalItems(itemsSize, goalId)
    }
}