package com.yilmaz.continuousgoals.presentation.secreens.goal_detail.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yilmaz.continuousgoals.domain.use_cases.goal_item.GetGoalItemsUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal_item.InsertGoalItemsUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal_item.UpdateGoalItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalItemViewModel @Inject constructor(
    private val getGoalItemsUseCase: GetGoalItemsUseCase,
    private val updateGoalItemUseCase: UpdateGoalItemUseCase,
    private val insertGoalItemsUseCase: InsertGoalItemsUseCase
) : ViewModel() {

    fun insertGoalItems(itemsSize: Int, goalId: Int) {
        viewModelScope.launch {
            insertGoalItemsUseCase.invoke(itemsSize, goalId)
        }
    }

}