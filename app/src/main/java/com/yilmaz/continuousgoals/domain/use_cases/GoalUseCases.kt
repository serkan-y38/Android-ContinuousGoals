package com.yilmaz.continuousgoals.domain.use_cases

data class GoalUseCases(
    val getAllGoalsUseCase: GetAllGoalsUseCase,
    val deleteGoalUseCase: DeleteGoalUseCase,
    val insertGoalUseCase: InsertGoalUseCase,
    val updateGoalUseCase: UpdateGoalUseCase
)
