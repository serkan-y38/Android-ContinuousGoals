package com.yilmaz.continuousgoals.presentation.secreens.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.domain.use_cases.GoalUseCases
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
    private val goalUseCases: GoalUseCases
) : ViewModel() {

    private val _state = mutableStateOf(GoalState())
    val state: State<GoalState> = _state

    init {
        getGoals()
    }

    @ExperimentalCoroutinesApi
    private fun getGoals() {
        goalUseCases.getAllGoalsUseCase.invoke()
            .onEach { goals ->
                _state.value = state.value.copy(
                    goals = goals,
                )
            }.launchIn(viewModelScope)
    }

    fun deleteGoal(model: Goal) {
        viewModelScope.launch {
            goalUseCases.deleteGoalUseCase(model)
        }
    }

    fun updateGoal(model: Goal) {
        viewModelScope.launch {
            goalUseCases.updateGoalUseCase(model)
        }
    }

    fun insertGoal(model: Goal) {
        viewModelScope.launch {
            goalUseCases.insertGoalUseCase(model)
        }
    }

}