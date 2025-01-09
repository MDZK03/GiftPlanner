package org.minhduc.giftplanner.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.ui.theme.Gift

/**
 * Created by Minh Duc on 09/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

data class NavigationItems(
    val title: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

val items = listOf(
    NavigationItems(R.string.nav_home, Icons.Filled.Home, Icons.Outlined.Home, Screen.MainScreen.route),
    NavigationItems(R.string.nav_people, Icons.Filled.Face, Icons.Outlined.Face, Screen.PeopleListScreen.route),
    NavigationItems(R.string.nav_gift, Gift, Gift, Screen.GiftListScreen.route)
)