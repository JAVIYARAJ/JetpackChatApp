package com.example.jetpackdesign.data.model.message

import com.example.jetpackdesign.data.model.message.Message

data class UserChatModel(
    var name: String,
    var icon: Int,
    val lastMessage: Message?=null,
    var isGroup: Boolean = false,
)
