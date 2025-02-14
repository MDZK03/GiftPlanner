package org.minhduc.giftplanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Minh Duc on 20/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Preview
@Composable
fun TestPreview() {
    val tabs = listOf(
        TabItem("Home", Icons.Filled.Home),
        TabItem("Favorites", Icons.Filled.Favorite),
        TabItem("Profile", Icons.Filled.Person),
        TabItem("Add", Icons.Filled.Add)
    )
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column {

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            containerColor = Color.White,
            contentColor = Color.Gray,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    color = Color.Black,
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .fillMaxWidth()
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.padding(8.dp),
                    content = {
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = tab.title,
                                modifier = Modifier
                                    .padding(8.dp),
                                color = if (selectedTabIndex == index) Color.Black else Color.Gray
                            )
                        }
                    }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> {
                //Composible for tab1
            }
            1 -> {
                //Composible for tab2
            }
            2 -> {
                //Composible for tab3
            }
            3 -> {
                //Composible for tab4
            }
        }
    }
}

data class TabItem(val title:String, val icon: ImageVector)