package org.minhduc.giftplanner.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.minhduc.giftplanner.database.AppDatabase
import org.minhduc.giftplanner.database.likes.LikeDao
import org.minhduc.giftplanner.database.person.PersonDao

/**
 * Created by Minh Duc on 09/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun providePersonDao(appDatabase: AppDatabase): PersonDao {
        return appDatabase.personDao()
    }

    @Provides
    fun provideLikeDao(appDatabase: AppDatabase): LikeDao {
        return appDatabase.likeDao()
    }
}
