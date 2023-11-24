package com.example.jetpackdesign.data.auth

import com.example.jetpackdesign.util.Util

data class User(
    var id: String="",
    var name: String,
    var email: String,
    var password: String,
    var status: UserStatus = UserStatus.Online,
    var country: String="India",
    var language: String="English",
    var createdAt: String = Util.defaultDate(),
    var updatedAt: String = "",
    var description: String = "",
    var image: String = "",
)

enum class UserStatus {
    Online, Offline, Busy
}