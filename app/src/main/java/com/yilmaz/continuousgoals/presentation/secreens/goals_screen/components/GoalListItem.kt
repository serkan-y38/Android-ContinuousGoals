package com.yilmaz.continuousgoals.presentation.secreens.goals_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yilmaz.continuousgoals.common.Constants
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.presentation.navigation.NavigationGraph
import com.yilmaz.continuousgoals.presentation.navigation.NavigationHelper.GOAL_MODEL_KEY

@Composable
fun GoalListItem(it: Goal, navController: NavController) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp, 0.dp, 10.dp, 10.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = GOAL_MODEL_KEY,
                    value = it
                )
                navController.navigate(route = NavigationGraph.GoalDetailScreen.route)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(it.color),
            contentColor = CardDefaults.cardColors().contentColor,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor,
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 8.dp)
                .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp)),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = it.goalTitle
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 2.dp),
                    text = it.goalDescription,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5
                )
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun ListItemPreview() {
    val context = LocalContext.current
    GoalListItem(
        it = Goal(
            0,
            "First goal",
            "First goal description",
            "15/01/2023",
            "15/01/2024",
            Constants.colors.random(),
            false
        ),
        navController = NavController(context)
    )
}