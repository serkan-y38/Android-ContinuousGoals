package com.yilmaz.continuousgoals.presentation.secreens.goal_detail.components

import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.yilmaz.continuousgoals.common.Constants

data class PieChartData(
    var key: String?,
    var value: Float?
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalStatisticsBottomSheet(
    onDismiss: () -> Unit,
    achieved: Int,
    failed: Int,
    initialState: Int
) {
    val state = rememberModalBottomSheetState()
    val legendAndLabelColor = if (isSystemInDarkTheme()) Color.WHITE else Color.BLACK

    val getPieChartData = listOf(
        PieChartData("Achieved", achieved.toFloat()),
        PieChartData("Failed", failed.toFloat()),
        PieChartData("Initial", initialState.toFloat()),
    )

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
                    .width(300.dp)
                    .height(300.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                AndroidView(
                    modifier = Modifier.matchParentSize(),
                    factory = { context ->
                        PieChart(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )
                            animateY(1000)
                            setEntryLabelColor(legendAndLabelColor)
                            description.isEnabled = false
                            isDrawHoleEnabled = false
                            legend.isEnabled = true
                            legend.textSize = 12F
                            legend.textColor = legendAndLabelColor
                            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                        }
                    },
                    update = { chart ->
                        val pieEntries = ArrayList<PieEntry>().also { entries ->
                            getPieChartData.forEach { item ->
                                entries.add(PieEntry(item.value ?: 0.toFloat(), item.key ?: ""))
                            }
                        }
                        val pieDataSet = PieDataSet(pieEntries, "").apply {
                            colors = Constants.colorsForPieChart
                            yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                            xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                            valueTextColor = legendAndLabelColor
                            valueLineColor = legendAndLabelColor
                            valueTypeface = Typeface.DEFAULT_BOLD
                            valueTextSize = 16f
                            sliceSpace = 8f
                        }
                        chart.data = PieData(pieDataSet)
                        chart.invalidate()
                    }
                )
            }
        }
    }
}