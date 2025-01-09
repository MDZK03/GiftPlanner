package org.minhduc.giftplanner.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.minhduc.giftplanner.ui.theme.Gift
import org.minhduc.giftplanner.ui.theme.Green40

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Composable
fun BottomBar(isPeople: Boolean, onPeopleClick:() -> Unit, onGiftClick:() -> Unit) {
    BottomAppBar() {
        Row(
            modifier =
                if(isPeople) Modifier.weight(1f).fillMaxSize().background(Green40).clickable { onPeopleClick() }
                else Modifier.weight(1f).fillMaxHeight().clickable { onPeopleClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.Face, contentDescription = "People List", Modifier.padding(6.dp))
            Text("People List")
        }
        Row(
            modifier =
                if(!isPeople) Modifier.weight(1f).fillMaxSize().background(Green40).clickable { onGiftClick() }
                else Modifier.weight(1f).fillMaxHeight().clickable { onGiftClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(Gift, contentDescription = "Gift List", Modifier.padding(6.dp))
            Text("Gift List")
        }
    }
}

@Preview
@Composable
fun PreviewBB() {
    BottomBar(isPeople = true, {}) {

    }
}