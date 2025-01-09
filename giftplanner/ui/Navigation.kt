package org.minhduc.giftplanner.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.minhduc.giftplanner.ui.views.MainView
import org.minhduc.giftplanner.ui.views.crud.CreateEditPersonView
import org.minhduc.giftplanner.ui.views.person_detail.DetailedPersonView
import org.minhduc.giftplanner.viewmodel.PersonViewModel

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
    val personViewModel = hiltViewModel<PersonViewModel>()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainView(navController)
        }

        composable(Screen.CreateEditPersonScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                })
        ) {entry ->
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            CreateEditPersonView(id, navController, personViewModel)
        }

        composable(Screen.PersonDetailScreen.route) {
            DetailedPersonView(navController)
        }

    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object CreateEditPersonScreen : Screen("create_person_screen")
    data object PersonDetailScreen : Screen("person_detail_screen")
    data object PeopleListScreen : Screen("people_list_screen")
    data object GiftListScreen : Screen("gift_list_screen")
}