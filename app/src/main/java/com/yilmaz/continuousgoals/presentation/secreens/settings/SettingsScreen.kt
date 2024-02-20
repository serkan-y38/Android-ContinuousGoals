package com.yilmaz.continuousgoals.presentation.secreens.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yilmaz.continuousgoals.common.Constants
import com.yilmaz.continuousgoals.presentation.utils.DataStoreUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {

    val context = LocalContext.current
    val dataStoreUtils = DataStoreUtils(context)
    val coroutineScope = rememberCoroutineScope()

    val themes = Constants.themes
    val theme = dataStoreUtils.getTheme().collectAsState(initial = Constants.themes[0])
    var selectedOption by remember { mutableStateOf(theme.value) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                title = {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = "Settings",
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
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Theme",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        )
                        themes.forEach { option ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (option == theme.value),
                                    onClick = {
                                        selectedOption = option
                                        coroutineScope.launch {
                                            dataStoreUtils.setTheme(selectedOption)
                                        }
                                    }
                                )
                                Text(
                                    text = option,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
            SetSystemBarsColor(selectedOption)
        }
    )
}

@Composable
fun SetSystemBarsColor(theme: String) {
    rememberSystemUiController().also { systemUiController ->
        systemUiController.setSystemBarsColor(MaterialTheme.colorScheme.surface)
        if ((!isSystemInDarkTheme() && theme == Constants.themes[0]) || theme == Constants.themes[2])
            SideEffect { systemUiController.statusBarDarkContentEnabled = true }
        else
            SideEffect { systemUiController.statusBarDarkContentEnabled = false }
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun Settings() {
    val context = LocalContext.current
    SettingsScreen(navController = NavController(context))
}