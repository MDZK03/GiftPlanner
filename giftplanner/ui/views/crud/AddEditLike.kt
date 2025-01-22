package org.minhduc.giftplanner.ui.views.crud

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.ui.base.TopBar

/**
 * Created by Minh Duc on 21/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Composable
fun AddEditLike(isEdit: Boolean, navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(isMain = false, isCreate = false,
                title = if(isEdit) stringResource(R.string.tlt_edit_like) else stringResource(R.string.tlt_add_like),
                onNavIconClicked = { navController.navigateUp() },
                {}, {})
        },
    ) {padding ->
        Column(modifier = Modifier.padding(padding)) {

        }

    }
}