package org.minhduc.giftplanner.ui.views.person_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.database.person.Person
import org.minhduc.giftplanner.ui.base.TopBar
import org.minhduc.giftplanner.viewmodel.PersonViewModel

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Composable
fun DetailedPersonView(id: Long, navController: NavController) {
    val viewModel = hiltViewModel<PersonViewModel>()
    val personDetail = viewModel.getPerson(id).collectAsStateWithLifecycle(Person(name = ""))
    viewModel.personNameState = personDetail.value.name
    viewModel.dobStringState = personDetail.value.dobString
    viewModel.relationshipState = personDetail.value.relationship

    var showOptions by rememberSaveable { mutableStateOf(false) }

    var selectedOption by rememberSaveable { mutableStateOf("Likes") }

    Scaffold(
        topBar = {
            TopBar(
                isMain = false,
                isCreate = false,
                title = stringResource(id = R.string.tlt_detail_person),
                onNavIconClicked = { navController.navigateUp() },
                onActionButtonClicked = { showOptions = true }) {
                    DropdownMenu(expanded = showOptions, onDismissRequest = { showOptions = false }) {
                        DropdownMenuItem(
                            text = { Text("Archive This Person") },
                            onClick = { showOptions = false })
                        DropdownMenuItem(
                            text = { Text("Delete This Person") },
                            onClick = { showOptions = false })
                    }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Likes")
            }
        }
    ) { padValue ->
        Column(modifier = Modifier.fillMaxSize().padding(padValue)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (col1, col2) = createRefs()
                    Column(
                        modifier = Modifier.constrainAs(col1) {
                            top.linkTo(parent.top, 12.dp)
                            absoluteLeft.linkTo(parent.absoluteLeft)
                            absoluteRight.linkTo(col2.absoluteLeft)
                        },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(50.dp).clip(CircleShape)
                                .background(Color.White).border(2.dp, Color.White, CircleShape),
                            imageVector = Icons.Default.AccountCircle, contentDescription = "Avatar"
                        )
                        Text(
                            modifier = Modifier.padding(5.dp).width(200.dp),
                            text = viewModel.personNameState, fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(
                        modifier = Modifier.constrainAs(col2) {
                            top.linkTo(parent.top, 12.dp)
                            absoluteRight.linkTo(parent.absoluteRight, 8.dp)
                        },
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(modifier = Modifier.padding(2.dp), text = "Date of birth:\n" + viewModel.dobStringState)
                        Text(modifier = Modifier.padding(2.dp), text = "This person is your:\n" + viewModel.relationshipState)
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(3.dp)
            ) {
                Column (
                    modifier = Modifier.weight(1f).wrapContentHeight().border(1.dp, Color.Gray)
                        .clickable {
                            selectedOption = "Likes"
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(modifier = Modifier.padding(5.dp), text = "Likes")
                }
                Column (
                    modifier = Modifier.weight(1f).wrapContentHeight().border(1.dp, Color.Gray)
                        .clickable {
                            selectedOption = "Dislikes"
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(modifier = Modifier.padding(5.dp), text = "Dislikes")
                }
                Column (
                    modifier = Modifier.weight(1f).wrapContentHeight().border(1.dp, Color.Gray)
                        .clickable {
                            selectedOption = "Gifts"
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(modifier = Modifier.padding(5.dp), text = "Gift Ideas")
                }
            }

            when (selectedOption) {
                "Likes" -> {
                    LikesList(0)
                }
                "Dislikes" -> {
                    DislikesList(id = 0)
                }
                else -> IdeasList(id = 0)
            }
        }
    }
}
