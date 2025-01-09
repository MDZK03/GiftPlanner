package org.minhduc.giftplanner.database.likes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLike(person: Like)

    @Query("SELECT * FROM likes_table ORDER BY ID")
    fun getLikeList(): Flow<List<Like>>

    @Update
    suspend fun updateLike(person: Like)

    @Delete
    suspend fun deleteLike(person: Like)

    @Query("DELETE FROM likes_table WHERE id = :id")
    fun deleteLikeById(id: Long)

    @Query("SELECT * FROM likes_table WHERE id = :id")
    fun getLike(id: Long): Flow<Like>
}