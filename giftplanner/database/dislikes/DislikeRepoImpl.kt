package org.minhduc.giftplanner.database.dislikes

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Minh Duc on 17/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */
class DislikeRepoImpl @Inject constructor(private val dislikeDao: DislikeDao): DislikeRepo {
    override fun getDislikeList(): Flow<List<Dislike>> = dislikeDao.getDislikeList()

    @WorkerThread
    override suspend fun insertDislike(dislike: Dislike) { dislikeDao.insertDislike(dislike) }

    @WorkerThread
    override suspend fun updateDislike(dislike: Dislike) { dislikeDao.updateDislike(dislike) }

    @WorkerThread
    override suspend fun deleteDislike(dislike: Dislike) { dislikeDao.deleteDislike(dislike) }

    override fun deleteDislikeById(id: Long) { dislikeDao.deleteDislikeById(id) }

    override fun getDislike(id: Long): Flow<Dislike> = dislikeDao.getDislike(id)
}