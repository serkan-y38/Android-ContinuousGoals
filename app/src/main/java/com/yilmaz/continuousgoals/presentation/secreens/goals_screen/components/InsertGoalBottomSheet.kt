package com.yilmaz.continuousgoals.presentation.secreens.goals_screen.components

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yilmaz.continuousgoals.domain.model.Goal
import com.yilmaz.continuousgoals.presentation.secreens.view_model.GoalViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertGoalBottomSheet(
    onDismiss: () -> Unit,
    goalViewModel: GoalViewModel = hiltViewModel()
) {
    val state = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    fun convertMillisToDate(millis: Long): String = SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.US
    ).format(Date(millis))

    ModalBottomSheet(
        modifier = Modifier
            .fillMaxSize(),
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        },
        onDismissRequest = {
            onDismiss()
        },
        sheetState = state,
        windowInsets = WindowInsets.ime
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val titleText = remember {
                mutableStateOf("")
            }
            val descriptionText = remember {
                mutableStateOf("")
            }
            val startDate = remember {
                mutableLongStateOf(0)
            }
            var showStartDatePicker by remember {
                mutableStateOf(false)
            }
            val endDate = remember {
                mutableLongStateOf(0)
            }
            var showEndDatePicker by remember {
                mutableStateOf(false)
            }
            val endDateClickableEnabled = remember {
                mutableStateOf(false)
            }
            var titleTextErrorState by remember {
                mutableStateOf(false)
            }

            TextField(
                singleLine = true,
                value = titleText.value,
                onValueChange = { text ->
                    titleText.value = text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = {
                    Text("Title")
                },
                supportingText = {
                    if (titleTextErrorState) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Type title please",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                isError = titleTextErrorState,
                trailingIcon = {
                    if (titleTextErrorState)
                        Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                },
            )
            TextField(
                value = descriptionText.value,
                onValueChange = { text ->
                    descriptionText.value = text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = {
                    Text("Description")
                }
            )
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 15.dp)
                    .clickable(true) {
                        showStartDatePicker = true
                    }
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(start = 16.dp),
                        text = if (startDate.longValue == 0.toLong())
                            "Select start date"
                        else
                            convertMillisToDate(startDate.longValue)
                    )
                }
            }
            if (showStartDatePicker) {
                MyDatePickerDialog(
                    onDateSelected = { date ->
                        if (date != 0.toLong()) {
                            startDate.longValue = date
                            // after start date selected, end date can be selected
                            endDateClickableEnabled.value = true
                            endDate.longValue = 0
                        }
                    },
                    onDismiss = { showStartDatePicker = false },
                    minSelectableDate = System.currentTimeMillis() - 86400000
                )
            }
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(top = 15.dp)
                    .clickable(endDateClickableEnabled.value) {
                        showEndDatePicker = true
                    }
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .padding(start = 16.dp),
                        text = if (endDate.longValue == 0.toLong())
                            "Select end date"
                        else
                            convertMillisToDate(endDate.longValue)
                    )
                }
            }
            if (showEndDatePicker) {
                MyDatePickerDialog(
                    onDateSelected = { date ->
                        if (date != 0.toLong())
                            endDate.longValue = date
                    },
                    onDismiss = { showEndDatePicker = false },
                    minSelectableDate = startDate.longValue + 86400000
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 15.dp),
                onClick = {
                    titleTextErrorState = titleText.value.isEmpty()
                    if (titleTextErrorState)
                    // After 5 second, error will be gone
                        Handler(Looper.getMainLooper()).postDelayed(
                            { titleTextErrorState = false },
                            5000
                        )

                    if (!titleTextErrorState && startDate.longValue != 0.toLong() && endDate.longValue != 0.toLong()) {
                        goalViewModel.insertGoal(
                            Goal(
                                null,
                                titleText.value,
                                descriptionText.value,
                                convertMillisToDate(startDate.longValue),
                                convertMillisToDate(endDate.longValue)
                            )
                        )
                        scope.launch { state.hide() }.invokeOnCompletion {
                            onDismiss()
                        }
                    }
                }
            ) {
                Text(
                    text = "Save",
                )
            }
        }

    }
}