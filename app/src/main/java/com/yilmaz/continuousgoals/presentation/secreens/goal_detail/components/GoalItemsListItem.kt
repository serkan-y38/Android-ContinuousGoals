package com.yilmaz.continuousgoals.presentation.secreens.goal_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yilmaz.continuousgoals.R
import com.yilmaz.continuousgoals.domain.model.GoalItem

@Composable
fun GoalItemsListItem(it: GoalItem) {
    ElevatedCard(
        modifier = Modifier
            .wrapContentSize(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        if (it.isInitialState)
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.baseline_outlined_flag_24_initial),
                contentDescription = "image"
            )
        else
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                painter = painterResource(
                    id = if (it.isDone) R.drawable.baseline_flag_24_done
                    else R.drawable.baseline_flag_24_failed
                ),
                contentDescription = "image"
            )
    }
}