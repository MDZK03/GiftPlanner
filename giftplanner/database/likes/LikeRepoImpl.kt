package org.minhduc.giftplanner.database.likes

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */
class LikeRepoImpl @Inject constructor(private val likeDao: LikeDao): LikeRepo {
    override fun getLikeList(): Flow<List<Like>> = likeDao.getLikeList()

    @WorkerThread
    override suspend fun insertLike(like: Like) { likeDao.insertLike(like) }

    @WorkerThread
    override suspend fun updateLike(like: Like) { likeDao.updateLike(like) }

    @WorkerThread
    override suspend fun deleteLike(like: Like) { likeDao.deleteLike(like) }

    override fun deleteLikeById(id: Long) { likeDao.deleteLikeById(id) }

    override fun getLike(id: Long): Flow<Like> = likeDao.getLike(id)

}