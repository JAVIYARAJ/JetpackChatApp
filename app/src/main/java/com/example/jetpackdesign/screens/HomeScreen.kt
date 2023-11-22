package com.example.jetpackdesign.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.Routes
import com.example.jetpackdesign.component.CustomTopBar
import com.example.jetpackdesign.component.DrawerItem
import com.example.jetpackdesign.component.FunctionalityDialog
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.state.DrawerItemState
import com.example.jetpackdesign.util.Constant
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(controller: NavHostController) {

    var functionalityDialog by remember {
        mutableStateOf(false)
    }

    if (functionalityDialog) {
        FunctionalityDialog {
            functionalityDialog = false
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val commonPaddingModifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)

    val drawerItemState = DrawerItemState(drawerItems = FakeData.DRAWER_ITEM)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column() {
                    Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
                    Row(
                        modifier = commonPaddingModifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_app_icon),
                            contentDescription = "drawer image",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .clickable {
                                    scope.launch {
                                        if (drawerState.isOpen) {
                                            drawerState.close()
                                        } else {
                                            drawerState.open()
                                        }
                                    }
                                },
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = Constant.APP_TITLE,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.weight(5f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(
                                    CircleShape
                                )
                                .clickable {
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    controller.navigate(Routes.ProfileRoute.route + "/${FakeData.CURRENT_USER.id}/${FakeData.CURRENT_USER.name}/${FakeData.CURRENT_USER.email}/${FakeData.CURRENT_USER.description}/${FakeData.CURRENT_USER.image}")
                                }
                        )
                    }
                    Divider(
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(modifier = commonPaddingModifier.fillMaxWidth()) {
                        items(drawerItemState.drawerItem) {
                            DrawerItem(
                                title = it.title,
                                icon = it.itemIcon,
                                onTap = {
                                    drawerItemState.changeTabIndex(it.title)

                                    scope.launch {
                                        drawerState.close()
                                    }
                                    when (it.title) {
                                        Constant.HOME -> {
                                            controller.navigate(Routes.HomeRoute.route)
                                        }

                                        Constant.CHAT -> {
                                            controller.navigate(Routes.ChatUserRoute.route)
                                        }

                                        Constant.CALLS -> {
                                            controller.navigate(Routes.CallRoute.route)
                                        }

                                        Constant.SETTINGS -> {
                                            functionalityDialog = true
                                        }

                                        Constant.PEOPLE -> {
                                            controller.navigate(Routes.PeopleRoute.route)
                                        }

                                        Constant.LOGOUT -> {
                                            functionalityDialog = true
                                        }
                                    }

                                },
                                isActive = it.isActiveIcon
                            )
                        }
                    }


                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CustomTopBar(
                    title = "Chat App",
                    onIconTab = {
                        scope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    },
                    appIcon = R.drawable.ic_app_icon,
                    iconDescription = "app_icon",
                    isForMainScreen = true
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {

                Text(text = "Home")
            }
        }
    }
}