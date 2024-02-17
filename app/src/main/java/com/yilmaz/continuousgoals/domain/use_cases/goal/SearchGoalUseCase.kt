package com.yilmaz.continuousgoals.domain.use_cases.goal

import com.yilmaz.continuousgoals.domain.repository.GoalRepository
import javax.inject.Inject

class SearchGoalUseCase @Inject constructor(
    private val repository: GoalRepository
) {
    operator fun invoke(query: String) = repository.search(query)
}