package com.example.jetpackdesign

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpackdesign.screens.ChatUserListScreen
import com.example.jetpackdesign.screens.ChatScreen
import com.example.jetpackdesign.screens.LoginScreen
import com.example.jetpackdesign.screens.ProfileScreen

@Composable
fun NavGraph(navHostController: NavHostController) {


    NavHost(navController = navHostController, startDestination = Routes.LoginScreen.route) {
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

        composable(Routes.LoginScreen.route) {
            LoginScreen(navHostController)
        }

        composable(
            Routes.ProfileScreen.route + "/{id}/{name}/{email}/{description}/{image}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                    defaultValue = "111"
                },
                navArgument(name = "name") {
                    type = NavType.StringType
                    defaultValue = "test user"
                },
                navArgument(name = "email") {
                    type = NavType.StringType
                    defaultValue = "test@jetpackmail.com"
                },
                navArgument(name = "description") {
                    type = NavType.StringType
                    defaultValue = "jetpack Developer"
                },
                navArgument(name = "image") {
                    type = NavType.IntType
                    defaultValue = R.drawable.ic_user1
                },
            )
        ) { backStack ->
            val id = requireNotNull(backStack.arguments?.getString("id"))
            val name = requireNotNull(backStack.arguments?.getString("name"))
            val email = requireNotNull(backStack.arguments?.getString("email"))
            val description = requireNotNull(backStack.arguments?.getString("description"))
            val image = requireNotNull(backStack.arguments?.getInt("image"))
            ProfileScreen(id, name, description, email, image)
        }
    }
}