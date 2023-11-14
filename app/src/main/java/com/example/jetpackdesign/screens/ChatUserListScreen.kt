package com.example.jetpackdesign.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.Routes
import com.example.jetpackdesign.component.ChatUserCard
import com.example.jetpackdesign.component.CustomTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatUserListScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "ChatApp", actions = {
                                                      Icon(imageVector = Icons.Default.Search, contentDescription = "˳")
            }, onIconTab = {}, appIcon =R.drawable.chat,"")
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(it)) {
            items(20) {
                val cardColor =
                    if (it % 2 == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiary
                ChatUserCard(cardColor, onTab = {
                    navHostController.navigate(Routes.HomeScreen.route)
                })
            }
        }
    }
}
