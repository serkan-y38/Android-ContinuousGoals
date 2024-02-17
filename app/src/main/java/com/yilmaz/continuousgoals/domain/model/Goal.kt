package com.yilmaz.continuousgoals.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yilmaz.continuousgoals.common.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "goal_table")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "goal_id") val
    goalId: Int?,
    @ColumnInfo(name = "goal_title") var goalTitle: String,
    @ColumnInfo(name = "goal_description") var goalDescription: String,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "end_date") val endDate: String,
    @ColumnInfo(name = "color") val color: Long = Constants.colors.shuffled().random(),
    @ColumnInfo(name = "is_goal_items_created") val isGoalItemsCreated: Boolean,
) : Parcelable