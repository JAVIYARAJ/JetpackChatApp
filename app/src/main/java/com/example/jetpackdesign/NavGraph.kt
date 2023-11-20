package com.example.jetpackdesign

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpackdesign.screens.ChatUserListScreen
import com.example.jetpackdesign.screens.ChatScreen

@Composable
fun NavGraph(navHostController: NavHostController) {


    NavHost(navController = navHostController, startDestination = Routes.ChatUserScreen.route) {
        composable(
            Routes.HomeScreen.route + "/{name}/{icon}",
            arguments = listOf(
                navArgument(name = "name") {
                    type = NavType.StringType
                    defaultValue = "test"
                },
                navArgument(name = "icon") {
                    type = NavType.IntType
                    defaultValue = R.drawable.ic_user1
                },

            )
        ) { backStack ->
            val name = requireNotNull(backStack.arguments?.getString("name"))
            val icon = requireNotNull(backStack.arguments?.getInt("icon"))
            ChatScreen(name, icon, navHostController)
        }
        composable(Routes.ChatUserScreen.route) {
            ChatUserListScreen(navHostController)
        }
    }
}