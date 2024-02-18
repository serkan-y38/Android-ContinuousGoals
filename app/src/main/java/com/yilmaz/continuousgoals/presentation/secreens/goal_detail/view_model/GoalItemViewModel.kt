package com.yilmaz.continuousgoals.presentation.secreens.goal_detail.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.domain.model.GoalItem
import com.yilmaz.continuousgoals.domain.use_cases.goal.UpdateGoalUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal_item.GetGoalItemsUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal_item.GetInitialStateGoalItemUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal_item.InsertGoalItemsUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal_item.UpdateGoalItemUseCase
import com.yilmaz.continuousgoals.presentation.secreens.goal_detail.GoalItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalItemViewModel @Inject constructor(
    private val getGoalItemsUseCase: GetGoalItemsUseCase,
    private val updateGoalItemUseCase: UpdateGoalItemUseCase,
    private val updateGoalUseCase: UpdateGoalUseCase,
    private val insertGoalItemsUseCase: InsertGoalItemsUseCase,
    private val getInitialStateGoalItemUseCase: GetInitialStateGoalItemUseCase
) : ViewModel() {

    private val _state = mutableStateOf(GoalItemState())
    val state: State<GoalItemState> = _state

    private val _initialGoalItemState = mutableStateOf(GoalItemState())
    val initialGoalItemState: State<GoalItemState> = _initialGoalItemState

    @ExperimentalCoroutinesApi
    fun getGoalItems(goalId: Int) {
        getItems(goalId)
    }

    private fun getItems(goalId: Int) {
        getGoalItemsUseCase.invoke(goalId)
            .onEach { items ->
                _state.value = state.value.copy(
                    goalItems = items,
                )
            }.launchIn(viewModelScope)
    }

    @ExperimentalCoroutinesApi
    fun getInitialStateGoalItem(goalId: Int) {
        getInitialStateGoalItemUseCase.invoke(goalId)
            .onEach { item ->
                _initialGoalItemState.value = initialGoalItemState.value.copy(
                    initialStateGoalItem = item,
                )
            }.launchIn(viewModelScope)
    }

    fun insertGoalItems(itemsSize: Int, goalId: Int) {
        viewModelScope.launch {
            insertGoalItemsUseCase.invoke(itemsSize, goalId)
        }
    }

    fun updateGoalItem(model: GoalItem) {
        viewModelScope.launch {
            updateGoalItemUseCase.invoke(model)
        }
    }

    fun updateGoal(model: Goal) {
        viewModelScope.launch {
            updateGoalUseCase.invoke(model)
        }
    }

}