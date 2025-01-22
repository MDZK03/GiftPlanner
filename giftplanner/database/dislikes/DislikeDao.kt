package org.minhduc.giftplanner.database.dislikes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DislikeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDislike(person: Dislike)

    @Query("SELECT * FROM dislikes_table ORDER BY ID")
    fun getDislikeList(): Flow<List<Dislike>>

    @Update
    suspend fun updateDislike(person: Dislike)

    @Delete
    suspend fun deleteDislike(person: Dislike)

    @Query("DELETE FROM dislikes_table WHERE id = :id")
    fun deleteDislikeById(id: Long)

    @Query("SELECT * FROM dislikes_table WHERE id = :id")
    fun getDislike(id: Long): Flow<Dislike>
}