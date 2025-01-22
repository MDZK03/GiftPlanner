package org.minhduc.giftplanner.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.ui.Screen
import org.minhduc.giftplanner.ui.base.CommonUtils
import org.minhduc.giftplanner.viewmodel.PersonViewModel

/**
 * Created by Minh Duc on 15/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Composable
fun PeopleListView(navController: NavController) {
    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState()
    val viewModel = hiltViewModel<PersonViewModel>()

    val personList = viewModel.getPeople.collectAsStateWithLifecycle(listOf())
    if (personList.value.isEmpty()) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.des_empty_list, "people")
            )
        }
    } else {
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            items(personList.value, { person -> person.id }) { person ->
                val dismissState = rememberSwipeToDismissBoxState (
                    confirmValueChange = {
                        when(it) {
                            SwipeToDismissBoxValue.StartToEnd,
                            SwipeToDismissBoxValue.EndToStart -> {
                                scope.launch {
                                    viewModel.deleteById(person.id)
                                }
                            }
                            SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState false
                        }
                        return@rememberSwipeToDismissBoxState true
                    },
                    positionalThreshold = { it * .35f }
                )
                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = { CommonUtils.DismissBackground(dismissState) },
                    content = {
                        CommonUtils.ItemList(person) {
                            val id = person.id
                            navController.navigate(Screen.PersonDetailScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}