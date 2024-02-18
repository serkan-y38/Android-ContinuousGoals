package com.yilmaz.continuousgoals.presentation.secreens.goal_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yilmaz.continuousgoals.presentation.secreens.goal_detail.GoalItemState

@Composable
fun GoalItemsList(state: GoalItemState) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = StaggeredGridCells.Fixed(6),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(state.goalItems) { goalItem ->
                GoalItemsListItem(it = goalItem)
            }
        }
    )
}