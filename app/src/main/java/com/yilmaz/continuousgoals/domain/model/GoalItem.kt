package com.yilmaz.continuousgoals.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "goal_item_table",
    foreignKeys = [ForeignKey(
        entity = Goal::class,
        parentColumns = arrayOf("goal_id"),
        childColumns = arrayOf("goal_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index("goal_id"),
    ]
)
data class GoalItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "goal_item_id")
    val goalItemId: Int?,
    @ColumnInfo(name = "goal_id") var goalId: Int?,
    @ColumnInfo(name = "is_initial_state") var isInitialState: Boolean,
    @ColumnInfo(name = "is_done") var isDone: Boolean,
)
