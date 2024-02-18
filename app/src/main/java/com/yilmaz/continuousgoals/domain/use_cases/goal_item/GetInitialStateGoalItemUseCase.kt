package com.yilmaz.continuousgoals.domain.use_cases.goal_item

import com.yilmaz.continuousgoals.domain.model.GoalItem
import com.yilmaz.continuousgoals.domain.repository.GoalItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInitialStateGoalItemUseCase @Inject constructor(
    private val repository: GoalItemRepository
) {
    operator fun invoke(goalId: Int): Flow<List<GoalItem>> =
        repository.getInitialStateGoalItem(goalId)
}