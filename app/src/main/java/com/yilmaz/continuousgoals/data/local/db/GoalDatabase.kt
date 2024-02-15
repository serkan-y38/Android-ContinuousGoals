package com.yilmaz.continuousgoals.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yilmaz.continuousgoals.data.local.dao.GoalDao
import com.yilmaz.continuousgoals.domain.model.Goal

@Database(
    entities = [Goal::class],
    version = 1,
    exportSchema = false
)
abstract class GoalDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
}