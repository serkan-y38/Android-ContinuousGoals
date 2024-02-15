package com.yilmaz.continuousgoals.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_table")
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "goal_title") var goalTitle: String,
    @ColumnInfo(name = "goal_description") var goalDescription: String,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "end_date") val endDate: String,
)
