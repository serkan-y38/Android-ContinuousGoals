package com.yilmaz.continuousgoals.di

import android.content.Context
import androidx.room.Room
import com.yilmaz.continuousgoals.common.Constants
import com.yilmaz.continuousgoals.data.local.dao.GoalDao
import com.yilmaz.continuousgoals.data.local.db.GoalDatabase
import com.yilmaz.continuousgoals.data.repository.GoalRepositoryImpl
import com.yilmaz.continuousgoals.domain.repository.GoalRepository
import com.yilmaz.continuousgoals.domain.use_cases.DeleteGoalUseCase
import com.yilmaz.continuousgoals.domain.use_cases.GetAllGoalsUseCase
import com.yilmaz.continuousgoals.domain.use_cases.GoalUseCases
import com.yilmaz.continuousgoals.domain.use_cases.InsertGoalUseCase
import com.yilmaz.continuousgoals.domain.use_cases.SearchGoalUseCase
import com.yilmaz.continuousgoals.domain.use_cases.UpdateGoalUseCase
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
    fun provideGoalUseCases(repository: GoalRepository) = GoalUseCases(
        getAllGoalsUseCase = GetAllGoalsUseCase(repository),
        deleteGoalUseCase = DeleteGoalUseCase(repository),
        updateGoalUseCase = UpdateGoalUseCase(repository),
        insertGoalUseCase = InsertGoalUseCase(repository),
    )

}