package org.minhduc.giftplanner.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.minhduc.giftplanner.ui.views.MainView
import org.minhduc.giftplanner.ui.views.crud.AddEditLike
import org.minhduc.giftplanner.ui.views.crud.CreateEditPersonView
import org.minhduc.giftplanner.ui.views.person_detail.DetailedPersonView

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController()
) {
//    val context = LocalContext.current
//    val displayMetrics = context.resources.displayMetrics
//    // Width and height of screen
//    val width = displayMetrics.widthPixels
//    val height = displayMetrics.heightPixels

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainView(navController)
        }

        composable(Screen.CreateEditPersonScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                })
        ) { entry ->
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            CreateEditPersonView(id, navController)
        }

        composable(Screen.PersonDetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                })
        ) { entry ->
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            DetailedPersonView(id, navController)
        }

        composable(Screen.AddEditLikeScreen.route + "/{isEdit}",
            arguments = listOf(
                navArgument("isEdit") {
                    type = NavType.BoolType
                    defaultValue = false
                    nullable = false
                })
        ) { entry ->
            val isEdit = if(entry.arguments != null)  entry.arguments!!.getBoolean("isEdit") else false
            AddEditLike(isEdit, navController)
        }
    }
}

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main")
    data object CreateEditPersonScreen : Screen("create_edit_person")
    data object AddEditLikeScreen : Screen("add_edit_like")
    data object PersonDetailScreen : Screen("person_detail")
    data object PeopleListScreen : Screen("people_list")
    data object GiftListScreen : Screen("gift_list")
}