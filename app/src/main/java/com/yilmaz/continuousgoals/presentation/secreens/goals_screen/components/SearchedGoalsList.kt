package com.yilmaz.continuousgoals.presentation.secreens.goals_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(state.searchedGoals) { goal ->
                GoalListItem(
                    it = goal,
                    navController = navController
                )
            }
        }
    )
}