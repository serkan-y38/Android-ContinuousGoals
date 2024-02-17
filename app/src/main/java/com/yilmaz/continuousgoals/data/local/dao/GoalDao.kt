package com.yilmaz.continuousgoals.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.yilmaz.continuousgoals.domain.model.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(model: Goal)

    @Update
    suspend fun updateGoal(model: Goal)

    @Delete
    suspend fun deleteGoal(model: Goal)

    @Query("SELECT * FROM goal_table ORDER BY goal_id DESC")
    fun getAllGoals(): Flow<List<Goal>>

    @Query("SELECT * FROM goal_table WHERE goal_title LIKE :query OR goal_description LIKE:query")
    fun search(query: String): Flow<List<Goal>>

}