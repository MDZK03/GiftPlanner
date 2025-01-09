package org.minhduc.giftplanner.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.minhduc.giftplanner.database.likes.LikeRepo
import org.minhduc.giftplanner.database.likes.LikeRepoImpl
import org.minhduc.giftplanner.database.person.PersonRepo
import org.minhduc.giftplanner.database.person.PersonRepoImpl

/**
 * Created by Minh Duc on 09/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindsPersonRepo(personRepoImpl: PersonRepoImpl): PersonRepo

    @Binds
    fun bindsLikeRepo(likeRepoImpl: LikeRepoImpl): LikeRepo
}
