package com.yilmaz.continuousgoals.presentation.secreens.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yilmaz.continuousgoals.domain.use_cases.SearchGoalUseCase
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.GoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchGoalViewModel @Inject constructor(
    private val searchGoalUseCase: SearchGoalUseCase
) : ViewModel() {

    private val _searchState = mutableStateOf(GoalState())
    val searchState: State<GoalState> = _searchState

    @ExperimentalCoroutinesApi
    fun searchGoal(query: String) {
        searchGoalUseCase.invoke(query)
            .onEach { searchedGoals ->
                _searchState.value = searchState.value.copy(
                    searchedGoals = searchedGoals,
                )
            }.launchIn(viewModelScope)
    }

}