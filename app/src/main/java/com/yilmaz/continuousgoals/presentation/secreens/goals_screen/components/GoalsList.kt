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
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.presentation.secreens.common.SwipeToDelete

@Composable
fun GoalsList(
    goals: List<Goal>,
    navController: NavController,
    ondDelete: (Goal) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(
                items = goals,
                key = { it.goalId!! }
            ) { goal ->
                SwipeToDelete(
                    item = goal,
                    onDelete = {
                        ondDelete(goal)
                    }
                ) {
                    GoalListItem(
                        it = goal,
                        navController = navController
                    )
                }

            }
        }
    )
}