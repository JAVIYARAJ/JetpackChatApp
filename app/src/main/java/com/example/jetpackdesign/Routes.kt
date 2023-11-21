package com.example.jetpackdesign

sealed class Routes(val route: String) {
    object HomeScreen : Routes("home_route")
    object ChatUserScreen : Routes("chat_user_screen_route")
    object ProfileScreen : Routes("profile")
    object LoginScreen : Routes("login")


}
