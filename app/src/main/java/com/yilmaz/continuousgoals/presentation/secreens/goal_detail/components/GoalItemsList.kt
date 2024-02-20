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
import com.yilmaz.continuousgoals.domain.model.GoalItem

@Composable
fun GoalItemsList(goalItems: List<GoalItem>) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        columns = StaggeredGridCells.Fixed(6),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(
                items = goalItems,
                key = { it.goalItemId!! }
            ) { goalItem -> GoalItemsListItem(it = goalItem) }
        }
    )
}