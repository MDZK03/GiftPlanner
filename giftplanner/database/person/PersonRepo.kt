package org.minhduc.giftplanner.database.person

import kotlinx.coroutines.flow.Flow

interface PersonRepo {
    fun getPeople(): Flow<List<Person>>

    suspend fun insertPerson(person: Person)

    suspend fun updatePerson(person: Person)

    suspend fun deletePerson(person: Person)

    fun deleteById(id: Long)

    fun getPerson(id: Long): Flow<Person>
}