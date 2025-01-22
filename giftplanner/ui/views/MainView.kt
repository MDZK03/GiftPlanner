package org.minhduc.giftplanner.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.minhduc.giftplanner.R
import org.minhduc.giftplanner.ui.Screen
import org.minhduc.giftplanner.ui.base.BottomBar
import org.minhduc.giftplanner.ui.base.CommonUtils.SmallMenu
import org.minhduc.giftplanner.ui.base.TopBar
import org.minhduc.giftplanner.ui.items

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Composable
fun MainView(navController: NavController) {
    val scope = rememberCoroutineScope()
    var isPeopleList by rememberSaveable { mutableStateOf(true) }
    var showOptions by rememberSaveable { mutableStateOf(false) }

    var selectedItemIndex = 0
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(stringResource(item.title)) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch { drawerState.close() }
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) { item.selectedIcon }
                                else item.unselectedIcon,
                                contentDescription = stringResource(item.title)
                            )
                        },
                        modifier = Modifier.run { padding(NavigationDrawerItemDefaults.ItemPadding) }
                    )
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                TopBar(isMain = true,
                    isCreate = false,
                    title = stringResource(id = R.string.tlt_main),
                    onNavIconClicked = {
                        scope.launch {
                            drawerState.apply { if (isClosed) open() else close() }
                        }
                    },
                    {}, {})
            },
            bottomBar = {
                BottomBar(isPeople = isPeopleList, onPeopleClick = { isPeopleList = true }) {
                    isPeopleList = false
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { showOptions = true }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    val default = 0L
                    SmallMenu(showOptions, stringResource(id = R.string.btn_add_person), stringResource(id = R.string.btn_add_gift),
                        { showOptions = false },
                        {
                            navController.navigate(Screen.CreateEditPersonScreen.route + "/$default")
                            showOptions = false
                        },
                        {
                            navController.navigate(Screen.PersonDetailScreen.route)
                            showOptions = false
                        }
                    )
                }
            }
        ) { padValue ->
            Column(
                modifier = Modifier.padding(padValue)
            ) {
                if (isPeopleList) {
                    PeopleListView(navController)
                } else {

                }
            }
        }
    }
}