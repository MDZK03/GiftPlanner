package org.minhduc.giftplanner.ui.views.crud

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.database.person.Person
import org.minhduc.giftplanner.ui.base.TopBar
import org.minhduc.giftplanner.viewmodel.PersonViewModel

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateEditPersonView(
    id: Long, navController: NavController, viewModel: PersonViewModel
) {
    var permissionRequestCompleted by rememberSaveable { mutableStateOf(false) }

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.READ_MEDIA_IMAGES,
//            Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
        )
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isGranted ->
    }

    if(id != 0L) {
        val person = viewModel.getPerson(id).collectAsStateWithLifecycle(initialValue = Person())
        viewModel.personNameState = person.value.name
        viewModel.dobState = person.value.birthday
        viewModel.relationshipState = person.value.relationship
    }
    
    Scaffold(
        topBar = {
            TopBar(isMain = false, isCreate = true,
                title = if(id != 0L) stringResource(R.string.create_person) else stringResource(R.string.edit_person),
                onNavIconClicked = { navController.navigateUp() },
                {}, {})
        },
    ) { padValue ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padValue),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Image(
                    modifier = Modifier.size(50.dp).clip(CircleShape).background(Color.White)
                        .border(2.dp, Color.White, CircleShape)
                        .clickable {
                            when {
                                permissionsState.allPermissionsGranted -> {
                                    Log.d("AAAA", "All permissions granted")
                                }
                                permissionsState.shouldShowRationale -> {
                                    permissionsState.launchMultiplePermissionRequest()
                                }
                                else -> {
                                    if(permissionRequestCompleted) {
                                        Log.d("AAAA", "DENIED")
                                    } else {
                                        permissionsState.launchMultiplePermissionRequest()
                                        permissionRequestCompleted = true
                                    }
                                }
                            }
                        }
                    ,
                    imageVector = Icons.Default.AccountCircle, contentDescription = "Avatar"
                )
            }
            OutlinedTextField(value = viewModel.personNameState, onValueChange = { viewModel.onNameChanged(it) })

            Log.d("AAAA", viewModel.personNameState)

//            OutlinedTextField(value = viewModel.dobState, onValueChange = { viewModel.onDobChanged(it) })

        }
    }
}