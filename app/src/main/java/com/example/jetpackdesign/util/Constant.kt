package com.example.jetpackdesign.util

import com.example.jetpackdesign.R
import com.example.jetpackdesign.data.model.Message
import com.example.jetpackdesign.data.model.UserChatModel

class Constant {
    companion object {

        //app text constant values
        const val JUMP_TO_BOTTOM = "jump to bottom"
        val CHAT_USER_LIST = arrayListOf(
            UserChatModel(
                name = "alex",
                icon = R.drawable.ic_user1,
                lastMessage = Message(message = "hi", user = "me", "08:21 AM")
            ),
            UserChatModel(
                name = "Emma",
                icon = R.drawable.ic_user2,
                lastMessage = Message(
                    message = "good morning",
                    user = "other",
                    timeStamp = "10:01 PM"
                )
            ),
            UserChatModel(
                name = "Joseph",
                icon = R.drawable.ic_user3,
                lastMessage = Message(message = "hello", user = "other", timeStamp = "03:18 PM")
            ),
            UserChatModel(
                name = "hussy",
                icon = R.drawable.ic_user2,
                lastMessage = Message(message = "how are you", user = "me", timeStamp = "01:20 AM")
            ),

            UserChatModel(
                name = "Olivia",
                icon = R.drawable.ic_user1,
                lastMessage = Message(message = "ok", user = "other", timeStamp = "12:00 PM")
            ),
            UserChatModel(
                name = "loe",
                icon = R.drawable.ic_user3,
                lastMessage = Message(message = "nice", user = "me", timeStamp = "02:12 AM")
            ),
            UserChatModel(
                name = "Elizabeth",
                icon = R.drawable.ic_user2,
                lastMessage = Message(message = "beautiful", user = "other", timeStamp = "08:10 PM")
            ),
            UserChatModel(
                name = "Android group",
                icon = R.drawable.ic_group_icon,
                lastMessage = Message(
                    message = "project review completed?",
                    user = "loe",
                    timeStamp = "06:14 AM"
                ),
                isGroup = true
            ),
            UserChatModel(
                name = "Ava",
                icon = R.drawable.ic_user2,
                lastMessage = Message(message = "good night", user = "other", "06:10 PM")
            ),
            UserChatModel(
                name = "Samuel",
                icon = R.drawable.ic_user1,
                lastMessage = Message(message = "hello", user = "me", timeStamp = "03:45 PM")
            ),
            UserChatModel(
                name = "Jack",
                icon = R.drawable.ic_user3,
                lastMessage = null
            ),
            UserChatModel(
                name = "Ios Group",
                icon = R.drawable.ic_group_icon,
                lastMessage = null,
                isGroup = true
            ),
        )

        val MESSAGES = listOf(
            Message(message = "hi", "me", timeStamp = "08:10 PM"),
            Message(message = "hello", "me", timeStamp = "08:10 PM"),
            Message(message = "hi", "javiya meet", timeStamp = "08:10 PM"),
            Message(
                message = "sample image",
                "javiya meet",
                timeStamp = "08:10 PM",
                image = R.drawable.sticker
            ),
            Message(message = "good", "me", timeStamp = "08:10 PM"),
            Message(message = "okay", "me", timeStamp = "08:10 PM"),
            Message(message = "okay", "me", timeStamp = "08:10 PM"),
            Message(message = "okay", "me", timeStamp = "08:10 PM"),
            Message(message = "okay", "me", timeStamp = "08:10 PM")
        )

    }
}