package com.example.jetpackdesign.data

import com.example.jetpackdesign.R
import com.example.jetpackdesign.data.model.DrawerItemModel
import com.example.jetpackdesign.data.model.Message
import com.example.jetpackdesign.data.model.ProfileItemModel
import com.example.jetpackdesign.data.model.UserChatModel
import com.example.jetpackdesign.data.model.UserModel
import com.example.jetpackdesign.util.Constant

class FakeData {
    companion object {

        val CURRENT_USER = UserModel(
            id = "101",
            "javiya raj",
            email = "raj@jetpackmail.com",
            description = "Android Developer",
            image = R.drawable.ic_user1
        )

        val CHAT_USER_LIST = arrayListOf(
            UserChatModel(
                name = "alex",
                icon = R.drawable.ic_user1,
                lastMessage = Message(message = "hi", user = CURRENT_USER, "08:21 AM")
            ),
            UserChatModel(
                name = "Emma",
                icon = R.drawable.ic_user2,
                lastMessage = Message(
                    message = "good morning",
                    user = UserModel(
                        id = "102",
                        "Emma",
                        description = "Flutter Developer",
                        email = "emma@jetpackmail.com",
                        image = R.drawable.ic_user2
                    ),
                    timeStamp = "10:01 PM"
                )
            ),
            UserChatModel(
                name = "Joseph",
                icon = R.drawable.ic_user3,
                lastMessage = Message(
                    message = "hello",
                    user = UserModel(
                        id = "102",
                        "Joseph",
                        description = "Flutter Developer",
                        email = "joseph@jetpackmail.com",
                        image = R.drawable.ic_user3
                    ),
                    timeStamp = "03:18 PM"
                )
            ),
            UserChatModel(
                name = "hussy",
                icon = R.drawable.ic_user2,
                lastMessage = Message(
                    message = "how are you",
                    user = CURRENT_USER,
                    timeStamp = "01:20 AM"
                )
            ),

            UserChatModel(
                name = "Olivia",
                icon = R.drawable.ic_user1,
                lastMessage = Message(
                    message = "ok",
                    user = UserModel(
                        id = "102",
                        "Olivia",
                        description = "Flutter Developer",
                        email = "olivia@jetpackmail.com",
                        image = R.drawable.ic_user1
                    ),
                    timeStamp = "12:00 PM"
                )
            ),
            UserChatModel(
                name = "loe",
                icon = R.drawable.ic_user3,
                lastMessage = Message(message = "nice", user = CURRENT_USER, timeStamp = "02:12 AM")
            ),
            UserChatModel(
                name = "Elizabeth",
                icon = R.drawable.ic_user2,
                lastMessage = Message(
                    message = "beautiful",
                    user = UserModel(
                        id = "102",
                        "Elizabeth",
                        description = "Flutter Developer",
                        email = "elizabeth@jetpackmail.com",
                        image = R.drawable.ic_user2
                    ),
                    timeStamp = "08:10 PM"
                )
            ),
            UserChatModel(
                name = "Android group",
                icon = R.drawable.ic_group_icon,
                lastMessage = Message(
                    message = "project review completed?",
                    user = UserModel(
                        id = "102",
                        "Android group",
                        description = "Flutter Developer",
                        email = "android@jetpackmail.com",
                        image = R.drawable.ic_group_icon
                    ),
                    timeStamp = "06:14 AM"
                ),
                isGroup = true
            ),
            UserChatModel(
                name = "Ava",
                icon = R.drawable.ic_user2,
                lastMessage = Message(
                    message = "good night",
                    user = UserModel(
                        id = "102",
                        "Ava",
                        description = "Flutter Developer",
                        email = "ava@jetpackmail.com",
                        image = R.drawable.ic_user2
                    ),
                    "06:10 PM"
                )
            ),
            UserChatModel(
                name = "Samuel",
                icon = R.drawable.ic_user1,
                lastMessage = Message(
                    message = "hello",
                    user = CURRENT_USER,
                    timeStamp = "03:45 PM"
                )
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
            Message(message = "hi", user = CURRENT_USER, timeStamp = "08:10 PM"),
            Message(message = "hello", user = CURRENT_USER, timeStamp = "08:10 PM"),
            Message(
                message = "hi",
                user = UserModel(
                    id = "102",
                    "Elizabeth",
                    description = "Flutter Developer",
                    email = "elizabeth@jetpackmail.com",
                    image = R.drawable.ic_user2
                ),
                timeStamp = "10:09 AM"
            ),
            Message(message = "good", user = CURRENT_USER, timeStamp = "08:10 PM"),
            Message(
                message = "hi",
                user = UserModel(
                    id = "102",
                    "Elizabeth",
                    description = "Flutter Developer",
                    email = "elizabeth@jetpackmail.com",
                    image = R.drawable.ic_user2
                ),
                timeStamp = "10:09 AM"
            ),
            Message(message = "okay", user = CURRENT_USER, timeStamp = "08:10 PM"),
            Message(message = "okay", user = CURRENT_USER, timeStamp = "08:10 PM"),
            Message(message = "okay", user = CURRENT_USER, timeStamp = "08:10 PM")
        )

        val DRAWER_ITEM = listOf(
            DrawerItemModel(title = Constant.HOME, itemIcon = R.drawable.ic_home_icon, true),
            DrawerItemModel(title = Constant.CHAT, itemIcon = R.drawable.ic_chat_icon),
            DrawerItemModel(title = Constant.PEOPLE, itemIcon = R.drawable.ic_user_icon),
            DrawerItemModel(title = Constant.CALLS, itemIcon = R.drawable.ic_call_icon),
            DrawerItemModel(title = Constant.SETTINGS, itemIcon = R.drawable.ic_settings_icon),
            DrawerItemModel(title = Constant.LOGOUT, itemIcon = R.drawable.ic_logout_icon)
        )

    }
}