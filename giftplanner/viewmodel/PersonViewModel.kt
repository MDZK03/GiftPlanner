package org.minhduc.giftplanner.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.minhduc.giftplanner.database.person.Person
import org.minhduc.giftplanner.database.person.PersonRepo
import javax.inject.Inject

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@HiltViewModel
class PersonViewModel @Inject constructor(private val repository: PersonRepo): ViewModel() {
    lateinit var getPeople: Flow<List<Person>>

    init {
        viewModelScope.launch {
            getPeople = repository.getPeople()
        }
    }

    var personNameState by mutableStateOf("")
    fun onNameChanged(nName: String) { personNameState = nName }

    var dobState by mutableLongStateOf(0L)

    var dobStringState by mutableStateOf("Not set")
    fun onDobChanged(nDob: String) { dobStringState = nDob }

    //change to mutableStateFlow
    var relationshipState by mutableStateOf("Not set")

    fun insert(person: Person) = viewModelScope.launch(Dispatchers.IO) { repository.insertPerson(person) }

    fun update(person: Person) = viewModelScope.launch(Dispatchers.IO) { repository.updatePerson(person) }

    fun delete(person: Person) = viewModelScope.launch(Dispatchers.IO) { repository.deletePerson(person) }

    fun deleteById(id: Long) = viewModelScope.launch(Dispatchers.IO) { repository.deleteById(id) }

    fun getPerson(id: Long): Flow<Person> { return repository.getPerson(id) }
}