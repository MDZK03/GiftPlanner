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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.ui.Screen
import org.minhduc.giftplanner.ui.base.TopBar
import org.minhduc.giftplanner.ui.theme.Green40

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedPersonView(navController: NavController) {
    var showOptions by rememberSaveable { mutableStateOf(false) }

    var showRelationship by rememberSaveable { mutableStateOf(false) }
    var selectedRelation by rememberSaveable { mutableStateOf("Relationship") }
    var selectedOption by rememberSaveable { mutableStateOf("Likes") }
    val options = listOf("Friend", "Family", "Lover/Spouse", "Coworker", "Acquaintance")

    Scaffold(
        topBar = {
            TopBar(
                isMain = false,
                isCreate = false,
                title = stringResource(id = R.string.detail_person),
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
        }
    ) { padValue ->
        Column(modifier = Modifier.fillMaxSize().padding(padValue)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(50.dp).clip(CircleShape).background(Color.White).border(2.dp, Color.White, CircleShape),
                        imageVector = Icons.Default.AccountCircle, contentDescription = "Avatar")
                    Text(modifier = Modifier.padding(5.dp), text = "Person's name", fontSize = 18.sp)
                }

                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(modifier = Modifier.padding(2.dp),text = "Date of birth: ")
                    Text(modifier = Modifier.padding(2.dp),text = "This person is your: ")
                    ExposedDropdownMenuBox(
                        modifier = Modifier.fillMaxWidth().height(50.dp).padding(2.dp),
                        expanded = showRelationship,
                        onExpandedChange = { showRelationship = false }
                    ) {
                        TextField(
                            readOnly = true,
                            value = selectedRelation,
                            onValueChange = {},
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = showRelationship)
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Green40
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = showRelationship,
                            onDismissRequest = { showRelationship = false }
                        ) {
                            options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                                    onClick = {
                                        selectedRelation = option
                                        showRelationship = false
                                    },
                                )
                            }
                        }
                    }

                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
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
                }
                "Dislikes" -> {
                    DislikesList(id = 0)
                }
                else -> IdeasList(id = 0)
            }
        }
    }
}
