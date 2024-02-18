package com.yilmaz.continuousgoals.presentation.secreens.goal_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yilmaz.continuousgoals.R
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.presentation.secreens.goal_detail.components.GoalItemsList
import com.yilmaz.continuousgoals.presentation.secreens.goal_detail.components.UpdateGoalItemDialog
import com.yilmaz.continuousgoals.presentation.secreens.goal_detail.view_model.GoalItemViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun GoalDetailScreen(
    navController: NavController,
    goal: Goal,
    goalItemViewModel: GoalItemViewModel = hiltViewModel()
) {

    fun getDifferenceInDays(dateStr1: String, dateStr2: String): Long {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return TimeUnit.MILLISECONDS.toDays(
            (dateFormat.parse(dateStr1) as Date).time - (dateFormat.parse(dateStr2) as Date).time
        )
    }

    LaunchedEffect(key1 = goal) {
        if (!goal.isGoalItemsCreated) {
            goalItemViewModel.insertGoalItems(
                getDifferenceInDays(goal.endDate, goal.startDate).toInt(),
                goal.goalId!!
            )
            goal.isGoalItemsCreated = true
            goalItemViewModel.updateGoal(
                model = goal
            )
        }
    }

    goalItemViewModel.getGoalItems(goal.goalId!!)
    val goalItemsState = goalItemViewModel.state.value

    goalItemViewModel.getInitialStateGoalItem(goal.goalId)
    val initialGoalItemState = goalItemViewModel.initialGoalItemState.value

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val openUpdateGoalItemDialogDialog = remember {
        mutableStateOf(false)
    }
    val hostState = remember {
        SnackbarHostState()
    }

    fun updateGoalItem(isAchieved: Boolean) {
        if (initialGoalItemState.initialStateGoalItem.isNotEmpty()) {
            val model = initialGoalItemState.initialStateGoalItem.first()
            model.isDone = isAchieved
            model.isInitialState = false
            goalItemViewModel.updateGoalItem(model)
            initialGoalItemState.initialStateGoalItem = emptyList()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(hostState)
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                title = {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = goal.goalTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )

        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_text_snippet_24),
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_delete_24),
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_pie_chart_24),
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { openUpdateGoalItemDialogDialog.value = true },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_flaky_24),
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { padding ->
            if (openUpdateGoalItemDialogDialog.value)
                UpdateGoalItemDialog(
                    onDismissRequest = {
                        updateGoalItem(isAchieved = false)
                        openUpdateGoalItemDialogDialog.value = false
                    },
                    onConfirmation = {
                        updateGoalItem(isAchieved = true)
                        openUpdateGoalItemDialogDialog.value = false
                    }
                )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                GoalItemsList(state = goalItemsState)
            }
        },
    )

}