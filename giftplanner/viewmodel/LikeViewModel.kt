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
import org.minhduc.giftplanner.database.likes.Like
import org.minhduc.giftplanner.database.likes.LikeRepo
import javax.inject.Inject

/**
 * Created by Minh Duc on 21/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@HiltViewModel
class LikeViewModel @Inject constructor(private val repository: LikeRepo): ViewModel() {
    lateinit var getPeople: Flow<List<Like>>

    init {
        viewModelScope.launch {
            getPeople = repository.getLikeList()
        }
    }

    fun insert(person: Like) = viewModelScope.launch(Dispatchers.IO) { repository.insertLike(person) }

    fun update(person: Like) = viewModelScope.launch(Dispatchers.IO) { repository.updateLike(person) }

    fun delete(person: Like) = viewModelScope.launch(Dispatchers.IO) { repository.deleteLike(person) }

    fun deleteById(id: Long) = viewModelScope.launch(Dispatchers.IO) { repository.deleteLikeById(id) }

    fun getLike(id: Long): Flow<Like> { return repository.getLike(id) }
}