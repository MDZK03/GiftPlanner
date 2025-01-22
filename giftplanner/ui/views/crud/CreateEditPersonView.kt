package org.minhduc.giftplanner.ui.views.crud

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.database.person.Person
import org.minhduc.giftplanner.ui.Screen
import org.minhduc.giftplanner.ui.base.CommonUtils.CustomTextField
import org.minhduc.giftplanner.ui.base.CommonUtils.DatePickerModalInput
import org.minhduc.giftplanner.ui.base.TopBar
import org.minhduc.giftplanner.ui.theme.Green40
import org.minhduc.giftplanner.viewmodel.PersonViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEditPersonView(id: Long, navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val viewModel = hiltViewModel<PersonViewModel>()
    var isEdit by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    var isNameMissing by rememberSaveable { mutableStateOf(false) }

    var showTimeInput by rememberSaveable { mutableStateOf(false) }
    var selectedDate by rememberSaveable { mutableLongStateOf(System.currentTimeMillis()) }

    var showRelationship by rememberSaveable { mutableStateOf(false) }
    var selectedRelation by rememberSaveable { mutableStateOf("Relationship (Optional)") }
    val options = listOf("Acquaintance", "Coworker", "Friend", "Family", "Lover/Spouse")

    if(id != 0L) {
        isEdit = true
        val person = viewModel.getPerson(id).collectAsStateWithLifecycle(Person(name = ""))
        viewModel.personNameState = person.value.name
        viewModel.dobState = person.value.birthday
        viewModel.relationshipState = person.value.relationship
    }
    
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopBar(isMain = false, isCreate = true,
                title = if(id != 0L) stringResource(R.string.tlt_create_person) else stringResource(R.string.tlt_edit_person),
                onNavIconClicked = { navController.navigateUp() },
                {}, {})
        },
    ) { padValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padValue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box {
                Image(
                    modifier = Modifier.size(80.dp).clip(CircleShape).background(Color.White).border(2.dp, Color.White, CircleShape),
                    imageVector = Icons.Default.AccountCircle, contentDescription = "Avatar"
                )
            }

            CustomTextField(
                modifier = Modifier.fillMaxWidth().padding(10.dp).align(Alignment.CenterHorizontally),
                label = "Name",
                supportingText = stringResource(id = R.string.des_person_name),
                false, isError = isNameMissing, Color.Gray,
                value = viewModel.personNameState,
                onValueChanged = { viewModel.onNameChanged(it) },
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth().padding(10.dp).align(Alignment.CenterHorizontally).clickable { showTimeInput = true },
                label = "Birthday (optional)", supportingText = null,
                true, isError = false, color = Color.Black,
                value = viewModel.dobStringState,
                onValueChanged = { viewModel.onDobChanged(it) },
            )
            if(showTimeInput) {
                DatePickerModalInput(
                    onDateSelected = {
                        if (it != null) {
                            selectedDate = it
                            viewModel.dobState = it
                        }
                        val dob = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date(selectedDate))
                        viewModel.dobStringState = dob

                        showTimeInput = false
                    },
                    onDismiss = { showTimeInput = false }
                )
            }

            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth().height(75.dp).align(Alignment.CenterHorizontally),
                expanded = showRelationship,
                onExpandedChange = { showRelationship = !showRelationship }
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(10.dp).menuAnchor(MenuAnchorType.PrimaryEditable, true),
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
                    onDismissRequest = { showRelationship = !showRelationship }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                            onClick = {
                                selectedRelation = option
                                viewModel.relationshipState = option
                                showRelationship = !showRelationship
                            },
                        )
                    }
                }
            }

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    if(viewModel.personNameState.isNotEmpty() && viewModel.personNameState.isNotBlank()) {
                        viewModel.insert(
                            Person(
                                name = viewModel.personNameState, birthday = viewModel.dobState,
                                dobString = viewModel.dobStringState,
                                relationship = viewModel.relationshipState
                            )
                        )
                        scope.launch {
                            navController.navigate(Screen.AddEditLikeScreen.route + "/$isEdit")
                        }
                    } else {
                        isNameMissing = true
                        scope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(
                                message = context.resources.getString(R.string.alert_required, "Name"),
                                withDismissAction = true,
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }){
                Text(text = "Next", style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}