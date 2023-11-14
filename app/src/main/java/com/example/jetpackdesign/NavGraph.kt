package com.example.jetpackdesign

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackdesign.screens.ChatScreen
import com.example.jetpackdesign.screens.ChatUserListScreen
import com.example.jetpackdesign.screens.HomeScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.ChatUserScreen.route) {
        composable(Routes.HomeScreen.route) {
            HomeScreen()
        }
        composable(Routes.ChatUserScreen.route) {
            ChatUserListScreen(navHostController)
        }
        composable(Routes.ChatScreen.route) {
            ChatScreen()
        }
    }
}