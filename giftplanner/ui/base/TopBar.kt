package org.minhduc.giftplanner.ui.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.ui.theme.Green80

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isMain: Boolean, isCreate: Boolean, title: String,
    onNavIconClicked:() -> Unit, onActionButtonClicked:() -> Unit,
    content: @Composable () -> Unit
) {
    if(isMain && !isCreate) {
        CenterAlignedTopAppBar(
            title = { Text(text = title, color = colorResource(id = R.color.white)) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Green80),
            navigationIcon = {
                IconButton(onClick = { onNavIconClicked() }) {
                    Icon(Icons.Default.Menu, "Menu", tint = Color.White)
                }
            },
        )
    } else if ((!isMain && isCreate)) {
        CenterAlignedTopAppBar(
            title = { Text(text = title, color = colorResource(id = R.color.white)) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Green80),
            navigationIcon = {
                IconButton(onClick = { onNavIconClicked() }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, "Menu", tint = Color.White)
                }
            }
        )
    } else {
        CenterAlignedTopAppBar(
            title = { Text(text = title, color = colorResource(id = R.color.white)) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Green80),
            navigationIcon = {
                IconButton(onClick = { onNavIconClicked() }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, "Menu", tint = Color.White)
                }
            },
            actions =  {
                IconButton(onClick = { onActionButtonClicked() }) {
                    Icon(Icons.Default.MoreVert, "More", tint = Color.White)
                    content()
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewTB() {
    TopBar(isMain = false, isCreate = true, title = stringResource(id = R.string.detail_person), {}, {}) {

    }
}