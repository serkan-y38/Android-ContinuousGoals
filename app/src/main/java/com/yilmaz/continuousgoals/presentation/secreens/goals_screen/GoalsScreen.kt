package com.yilmaz.continuousgoals.presentation.secreens.goals_screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yilmaz.continuousgoals.R
import com.yilmaz.continuousgoals.presentation.navigation.NavigationGraph
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.components.GoalsList
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.components.InsertGoalBottomSheet
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.components.navigationItems
import com.yilmaz.continuousgoals.presentation.secreens.goals_screen.view_model.GoalViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GoalsScreen(
    navController: NavController,
    goalViewModel: GoalViewModel = hiltViewModel()
) {
    var query by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val hostState = remember {
        SnackbarHostState()
    }
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val surfaceColor = MaterialTheme.colorScheme.surface
    val elevatedSurfaceColor = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp)
    val searchBarPadding: Dp by animateDpAsState(if (active) 0.dp else 10.dp, label = "")
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigationItems = navigationItems()

    val state = goalViewModel.state.value

    fun search() {
        if (query != "")
            goalViewModel.searchGoal(query = "%$query%")
        else
            state.searchedGoals = emptyList()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                navigationItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        selected = index == selectedItemIndex,
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                contentDescription = item.title,
                                imageVector = if (index == selectedItemIndex)
                                    item.selectedIcon
                                else
                                    item.unselectedIcon
                            )
                        },
                        onClick = {
                            selectedItemIndex = index
                            when (selectedItemIndex) {
                                0 -> {
                                    // TODO
                                }

                                1 -> {
                                    // TODO
                                }

                                2 -> navController.navigate(route = NavigationGraph.SettingsScreen.route)
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = hostState)
            },
            topBar = {
                SearchBar(
                    tonalElevation = 8.dp,
                    shadowElevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .animateContentSize()
                        .padding(horizontal = searchBarPadding)
                        .padding(bottom = 8.dp),
                    placeholder = {
                        Text(text = "Search")
                    },
                    onActiveChange = {
                        active = it
                        if (active)
                            systemUiController.setSystemBarsColor(elevatedSurfaceColor)
                        else
                            systemUiController.setSystemBarsColor(surfaceColor)
                    },
                    onQueryChange = {
                        query = it
                        search()
                    },
                    onSearch = {
                        query = it
                        search()
                    },
                    leadingIcon = {
                        if (active)
                            IconButton(onClick = {
                                systemUiController.setSystemBarsColor(surfaceColor)
                                state.searchedGoals = emptyList()
                                active = false
                                query = ""
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        else
                            IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                    },
                    trailingIcon = {
                        Row {
                            IconButton(
                                onClick = {
                                    if (!active) {
                                        active = true
                                        systemUiController.setSystemBarsColor(elevatedSurfaceColor)
                                    }
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                        }
                    },
                    query = query,
                    active = active,
                ) {
                    GoalsList(state.searchedGoals, navController)
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showBottomSheet = true },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "insert goal fab"
                    )
                }
            },
            content = { padding ->
                if (showBottomSheet)
                    InsertGoalBottomSheet(
                        onDismiss = {
                            showBottomSheet = false
                        },
                        onSaveGoal = { goal ->
                            goalViewModel.insertGoal(goal)
                        }
                    )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    GoalsList(state.goals, navController)
                }
            }
        )
    }
}