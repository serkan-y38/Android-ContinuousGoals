package com.yilmaz.continuousgoals.presentation.secreens.goals_screen.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.domain.use_cases.goal.DeleteGoalUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal.GetAllGoalsUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal.InsertGoalUseCase
import com.yilmaz.continuousgoals.domain.use_cases.goal.SearchGoalUseCase
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.GoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GoalViewModel @Inject constructor(
    private val getAllGoalsUseCase: GetAllGoalsUseCase,
    private val searchGoalUseCase: SearchGoalUseCase,
    private val insertGoalUseCase: InsertGoalUseCase,
    private val deleteGoalUseCase: DeleteGoalUseCase
) : ViewModel() {

    private val _state = mutableStateOf(GoalState())
    val state: State<GoalState> = _state

    init {
        getGoals()
    }

    @ExperimentalCoroutinesApi
    private fun getGoals() {
        getAllGoalsUseCase.invoke()
            .onEach { goals ->
                _state.value = state.value.copy(
                    goals = goals,
                )
            }.launchIn(viewModelScope)
    }

    @ExperimentalCoroutinesApi
    fun searchGoal(query: String) {
        searchGoalUseCase.invoke(query)
            .onEach { searchedGoals ->
                _state.value = state.value.copy(
                    searchedGoals = searchedGoals,
                )
            }.launchIn(viewModelScope)
    }

    fun insertGoal(model: Goal) {
        viewModelScope.launch {
            insertGoalUseCase.invoke(model)
        }
    }

    fun deleteGoal(model: Goal) {
        viewModelScope.launch {
            deleteGoalUseCase.invoke(model)
        }
    }

}