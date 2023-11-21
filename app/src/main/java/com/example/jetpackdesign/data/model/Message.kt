package com.example.jetpackdesign.data.model
data class Message(
    var message: String,
    var user: UserModel,
    var timeStamp: String,
    var image: Int? = null,
)
