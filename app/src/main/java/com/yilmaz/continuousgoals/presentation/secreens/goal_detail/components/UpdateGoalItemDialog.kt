package com.yilmaz.continuousgoals.presentation.secreens.goal_detail.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun UpdateGoalItemDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = "Update today's goal")
        },
        text = {
            Text(text = "Has today's goal been achieved or failed?")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Achieved")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Failed")
            }
        }
    )
}