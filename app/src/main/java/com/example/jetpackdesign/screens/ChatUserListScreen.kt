package com.example.jetpackdesign.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.Routes
import com.example.jetpackdesign.component.ChatUserCard
import com.example.jetpackdesign.component.CustomTopBar
import com.example.jetpackdesign.component.FunctionalityDialog
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.util.Constant
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatUserListScreen(navHostController: NavHostController) {

    var functionalityDialog by remember {
        mutableStateOf(false)
    }

    if (functionalityDialog) {
        FunctionalityDialog {
            functionalityDialog = false
        }
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Chat",
                onIconTab = { navHostController.popBackStack() },
                appIcon = R.drawable.ic_back_icon,
                iconDescription = "",
                isForMainScreen = true,
                actions = {
                    IconButton(onClick = { functionalityDialog = true }) {
                        Icon(Icons.Filled.Search, "")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    functionalityDialog = true
                },
                shape = RoundedCornerShape(10.dp),
                contentColor = MaterialTheme.colorScheme.primaryContainer,
                containerColor = Color(69, 179, 157)
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            items(FakeData.CHAT_USER_LIST.size) {
                ChatUserCard(onTab = {
                    navHostController.navigate(Routes.ChatRoute.route + "/${FakeData.CHAT_USER_LIST[it].name}/${if (FakeData.CHAT_USER_LIST[it].isGroup) R.drawable.ic_group_icon else FakeData.CHAT_USER_LIST[it].icon}")
                }, user = FakeData.CHAT_USER_LIST[it])
            }
        }
    }

}

@Preview
@Composable
fun CreateRoomChatButton() {
    FloatingActionButton(
        onClick = {

        },
        shape = RoundedCornerShape(10.dp),
        contentColor = MaterialTheme.colorScheme.primaryContainer,
        containerColor = Color(69, 179, 157)
    ) {
        Icon(Icons.Filled.Add, "")
    }
}
