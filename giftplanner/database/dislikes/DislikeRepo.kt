package org.minhduc.giftplanner.database.dislikes

import kotlinx.coroutines.flow.Flow

interface DislikeRepo {
    suspend fun insertDislike(dislike: Dislike)

    fun getDislikeList(): Flow<List<Dislike>>

    suspend fun updateDislike(dislike: Dislike)

    suspend fun deleteDislike(dislike: Dislike)

    fun deleteDislikeById(id: Long)

    fun getDislike(id: Long): Flow<Dislike>
}