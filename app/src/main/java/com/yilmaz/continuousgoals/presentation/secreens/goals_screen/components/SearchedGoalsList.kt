package com.yilmaz.continuousgoals.presentation.secreens.goals_screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.GoalState

@Composable
fun SearchedGoalsList(
    state: GoalState,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        state = rememberLazyListState()
    ) {
        items(state.searchedGoals) { goal ->
            GoalListItem(
                it = goal,
                navController = navController
            )
        }
    }
}