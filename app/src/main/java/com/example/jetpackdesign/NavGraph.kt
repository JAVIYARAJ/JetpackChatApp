package com.example.jetpackdesign

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetpackdesign.screens.CallScreen
import com.example.jetpackdesign.screens.ChatUserListScreen
import com.example.jetpackdesign.screens.ChatScreen
import com.example.jetpackdesign.screens.HomeScreen
import com.example.jetpackdesign.screens.LoginScreen
import com.example.jetpackdesign.screens.PeopleScreen
import com.example.jetpackdesign.screens.ProfileScreen
import com.example.jetpackdesign.screens.SelectPeopleScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(navHostController: NavHostController) {


    NavHost(navController = navHostController, startDestination = Routes.HomeRoute.route) {
        composable(
            Routes.ChatRoute.route + "/{name}/{icon}", arguments = listOf(
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
        composable(Routes.ChatUserRoute.route) {
            ChatUserListScreen(navHostController)
        }

        composable(Routes.HomeRoute.route) {
            HomeScreen(navHostController)
        }

        composable(Routes.LoginRoute.route) {
            LoginScreen(navHostController)
        }

        composable(Routes.PeopleRoute.route) {
            PeopleScreen(navHostController)
        }

        composable(Routes.CallRoute.route) {
            CallScreen(navHostController)
        }


        composable(Routes.CallRoute.route) {
            CallScreen(navHostController)
        }

        composable(Routes.SelectPeopleRoute.route) {
            SelectPeopleScreen(navHostController)
        }

        composable(
            Routes.ProfileRoute.route + "/{id}/{name}/{email}/{description}/{image}",
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
            ProfileScreen(name, description, email, image)
        }
    }
}