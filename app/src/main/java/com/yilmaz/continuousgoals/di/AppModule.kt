package com.yilmaz.continuousgoals.di

import android.content.Context
import androidx.room.Room
import com.yilmaz.continuousgoals.common.Constants
import com.yilmaz.continuousgoals.data.local.dao.GoalDao
import com.yilmaz.continuousgoals.data.local.dao.GoalItemDao
import com.yilmaz.continuousgoals.data.local.db.GoalDatabase
import com.yilmaz.continuousgoals.data.repository.GoalItemRepositoryImpl
import com.yilmaz.continuousgoals.data.repository.GoalRepositoryImpl
import com.yilmaz.continuousgoals.domain.repository.GoalItemRepository
import com.yilmaz.continuousgoals.domain.repository.GoalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGoalDataBase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        GoalDatabase::class.java,
        Constants.APP_DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideGoalDao(db: GoalDatabase): GoalDao = db.goalDao()

    @Provides
    @Singleton
    fun provideGoalRepository(dao: GoalDao): GoalRepository = GoalRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideGoalItemDao(db: GoalDatabase): GoalItemDao = db.goalItemDao()

    @Provides
    @Singleton
    fun provideGoalItemRepository(dao: GoalItemDao): GoalItemRepository = GoalItemRepositoryImpl(dao)

}