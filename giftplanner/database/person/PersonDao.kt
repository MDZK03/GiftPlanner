package org.minhduc.giftplanner.database.person

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person)

    @Query("SELECT * FROM PEOPLE_TABLE ORDER BY ID")
    fun getPeople(): Flow<List<Person>>

    @Update
    suspend fun updatePerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("DELETE FROM PEOPLE_TABLE WHERE id = :id")
    fun deletePersonById(id: Long)

    @Query("SELECT * FROM PEOPLE_TABLE WHERE id = :id")
    fun getPerson(id: Long): Flow<Person>
}