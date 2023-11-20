package com.example.jetpackdesign.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.Routes
import com.example.jetpackdesign.component.ChatUserCard
import com.example.jetpackdesign.component.CustomTopBar
import com.example.jetpackdesign.data.model.Message
import com.example.jetpackdesign.data.model.UserChatModel
import com.example.jetpackdesign.util.Constant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatUserListScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "ChatApp", actions = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "˳")
            }, onIconTab = {
                navHostController.popBackStack()
            }, appIcon = R.drawable.chat, "", isForMainScreen = true)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            items(Constant.CHAT_USER_LIST.size) {

                ChatUserCard(onTab = {
                    navHostController.navigate(Routes.HomeScreen.route + "/${Constant.CHAT_USER_LIST[it].name}/${if(Constant.CHAT_USER_LIST[it].isGroup) R.drawable.ic_group_icon else Constant.CHAT_USER_LIST[it].icon}")
                }, user = Constant.CHAT_USER_LIST[it])
            }
        }
    }
}
