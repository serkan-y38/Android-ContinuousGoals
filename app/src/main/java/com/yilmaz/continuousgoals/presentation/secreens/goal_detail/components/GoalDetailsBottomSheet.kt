package com.yilmaz.continuousgoals.presentation.secreens.goal_detail.components

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yilmaz.continuousgoals.common.StringHelper
import com.yilmaz.continuousgoals.domain.model.Goal
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalDetailsBottomSheet(
    onDismiss: () -> Unit,
    onUpdateGoal: (String, String) -> Unit,
    goal: Goal
) {
    val state = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val titleText = remember {
        mutableStateOf(goal.goalTitle)
    }
    val descriptionText = remember {
        mutableStateOf(goal.goalDescription)
    }
    var titleTextErrorState by remember {
        mutableStateOf(false)
    }

    fun updateButtonAction() {
        titleTextErrorState = titleText.value.isEmpty()

        if (titleTextErrorState)
        // After 5 second, error will be gone
            Handler(Looper.getMainLooper()).postDelayed({ titleTextErrorState = false }, 5000)

        if (!titleTextErrorState) {
            onUpdateGoal(
                StringHelper().capitalize(titleText.value),
                StringHelper().capitalize(descriptionText.value)
            )
            scope.launch { state.hide() }.invokeOnCompletion {
                onDismiss()
            }
        }
    }

    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        dragHandle = { BottomSheetDefaults.DragHandle() },
        onDismissRequest = { onDismiss() },
        sheetState = state,
        windowInsets = WindowInsets.ime
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "${goal.startDate} - ${goal.endDate}"
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            )
            TextField(
                singleLine = true,
                value = titleText.value,
                onValueChange = { text -> titleText.value = text },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                label = { Text("Title") },
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
                label = { Text("Description") }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 15.dp),
                onClick = { updateButtonAction() },
                content = { Text(text = "Save Changes") }
            )
        }
    }
}