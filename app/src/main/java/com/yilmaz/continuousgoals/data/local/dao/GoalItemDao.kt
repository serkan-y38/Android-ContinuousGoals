package com.yilmaz.continuousgoals.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.yilmaz.continuousgoals.domain.model.GoalItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoalItem(model: GoalItem)

    @Update
    suspend fun updateGoalItem(model: GoalItem)

    @Query("SELECT * FROM goal_item_table WHERE goal_id=:goalId ORDER BY goal_item_id ASC")
    fun getGoalItems(goalId: Int): Flow<List<GoalItem>>

    @Transaction
    suspend fun insertGoalItems(itemsSize: Int, goalId: Int) {
        for (i in 0..itemsSize)
            insertGoalItem(
                GoalItem(
                    goalItemId = null,
                    goalId = goalId,
                    isInitialState = true,
                    isDone = false
                )
            )
    }

}