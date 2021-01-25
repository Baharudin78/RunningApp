package com.baharudin.runningapp.di

import android.content.Context
import androidx.room.Room
import com.baharudin.runningapp.db.RunningDatabases
import com.baharudin.runningapp.other.Constant.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app : Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabases::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao (db : RunningDatabases) = db.getRunDao()
}