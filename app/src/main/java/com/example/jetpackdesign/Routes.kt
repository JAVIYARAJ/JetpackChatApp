package com.example.jetpackdesign

sealed class Routes(val route: String) {
    object HomeRoute : Routes("home_route")
    object TestRoute : Routes("test_route")

    object ChatRoute : Routes("chat_route")
    object ChatUserRoute : Routes("chat_user_screen_route")
    object ProfileRoute : Routes("profile_route")
    object LoginRoute : Routes("login_route")
    object PeopleRoute : Routes("people_route")
    object CallRoute : Routes("call_route")
    object SelectPeopleRoute : Routes("select_people_route")

}
