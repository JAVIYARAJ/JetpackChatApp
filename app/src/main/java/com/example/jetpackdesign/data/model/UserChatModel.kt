package com.example.jetpackdesign.data.model

data class UserChatModel(
    var name: String,
    var icon: Int,
    val lastMessage: Message?=null,
    var isGroup: Boolean = false,
)
