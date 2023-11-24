package com.example.jetpackdesign.data.model.message

import com.example.jetpackdesign.data.model.common.UserModel

data class Message(
    var message: String,
    var user: UserModel,
    var timeStamp: String,
    var image: Int? = null,
)
