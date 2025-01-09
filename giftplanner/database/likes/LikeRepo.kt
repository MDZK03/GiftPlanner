package org.minhduc.giftplanner.database.likes

import kotlinx.coroutines.flow.Flow

interface LikeRepo {

    suspend fun insertLike(like: Like)

    fun getLikeList(): Flow<List<Like>>

    suspend fun updateLike(like: Like)

    suspend fun deleteLike(like: Like)

    fun deleteLikeById(id: Long)

    fun getLike(id: Long): Flow<Like>
}