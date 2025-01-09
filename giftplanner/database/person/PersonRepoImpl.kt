package org.minhduc.giftplanner.database.person

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

class PersonRepoImpl @Inject constructor (private val personDao: PersonDao): PersonRepo {
    override fun getPeople(): Flow<List<Person>> = personDao.getPeople()

    @WorkerThread
    override suspend fun insertPerson(person: Person) { personDao.insertPerson(person) }

    @WorkerThread
    override suspend fun updatePerson(person: Person) { personDao.updatePerson(person) }

    @WorkerThread
    override suspend fun deletePerson(person: Person) { personDao.deletePerson(person) }

    @WorkerThread
    override fun deleteById(id: Long) { personDao.deletePersonById(id) }

    override fun getPerson(id: Long): Flow<Person> = personDao.getPerson(id)
}