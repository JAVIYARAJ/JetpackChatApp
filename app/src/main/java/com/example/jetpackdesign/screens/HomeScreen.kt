package com.example.jetpackdesign.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.Routes
import com.example.jetpackdesign.component.ChatUserCard
import com.example.jetpackdesign.component.CustomCenterTopBar
import com.example.jetpackdesign.component.CustomLeftSideTopBar
import com.example.jetpackdesign.component.DrawerItem
import com.example.jetpackdesign.component.FunctionalityDialog
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.state.DrawerItemState
import com.example.jetpackdesign.util.Constant
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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


    val tabItems = listOf(
        TabItem(
            title = "Chats", selectedIcon = Icons.Filled.Home, unSelectedIcon = Icons.Outlined.Home
        ), TabItem(
            title = "Status", selectedIcon = Icons.Filled.Star, unSelectedIcon = Icons.Outlined.Star
        ), TabItem(
            title = "Calls", selectedIcon = Icons.Filled.Call, unSelectedIcon = Icons.Outlined.Call
        )
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabItems.size
    }

    var isFabVisible by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(selectedTabIndex) {
        isFabVisible = selectedTabIndex == 0
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    var menuExpanded by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(pagerState.currentPage) {
        isFabVisible = pagerState.currentPage == 0
        selectedTabIndex = pagerState.currentPage
    }

    BackHandler {
        if (drawerState.isOpen) {
            scope.launch {
                drawerState.close()
            }
        } else {
            controller.popBackStack()
        }
    }

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
                        Image(painter = painterResource(id = R.drawable.img),
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
                                })
                    }
                    Divider(
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(modifier = commonPaddingModifier.fillMaxWidth()) {
                        items(drawerItemState.drawerItem) {
                            DrawerItem(
                                title = it.title, icon = it.itemIcon, onTap = {
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

                                }, isActive = it.isActiveIcon
                            )
                        }
                    }


                }
            }
        }, drawerState = drawerState
    ) {
        Scaffold(topBar = {
            CustomLeftSideTopBar(
                title = "ChatApp",
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Filled.Favorite, "more icon")
                    }
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Filled.Search, "more icon")
                    }
                    IconButton(onClick = {
                        menuExpanded = !menuExpanded
                    }) {
                        Icon(Icons.Filled.MoreVert, "more icon")
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        offset = DpOffset(20.dp, 5.dp)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("New group")
                            },
                            onClick = {
                                menuExpanded = false
                                functionalityDialog = true
                            },
                        )
                        DropdownMenuItem(
                            text = {
                                Text("New Broadcast")
                            },
                            onClick = {
                                menuExpanded = false
                                functionalityDialog = true
                            },
                        )
                        DropdownMenuItem(
                            text = {
                                Text("Linked devices")
                            },
                            onClick = {
                                menuExpanded = false
                                functionalityDialog = true
                            },
                        )
                        DropdownMenuItem(
                            text = {
                                Text("Starred messages")
                            },
                            onClick = {
                                menuExpanded = false
                                functionalityDialog = true
                            },
                        )
                        DropdownMenuItem(
                            text = {
                                Text("Payments")
                            },
                            onClick = {
                                menuExpanded = false
                                functionalityDialog = true
                            },
                        )
                        DropdownMenuItem(
                            text = {
                                Text("Settings")
                            },
                            onClick = {
                                menuExpanded = false
                                functionalityDialog = true
                            },
                        )
                    }
                }
            )
        }, floatingActionButton = {
            if (isFabVisible) {
                FloatingActionButton(
                    onClick = {
                        controller.navigate(Routes.SelectPeopleRoute.route)
                    },
                    shape = RoundedCornerShape(10.dp),
                    contentColor = MaterialTheme.colorScheme.primaryContainer,
                    containerColor = Color(69, 179, 157)
                ) {
                    Icon(Icons.Filled.Add, "")
                }
            }
        }) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {

                Surface(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TabRow(
                            selectedTabIndex = selectedTabIndex,
                        ) {
                            tabItems.forEachIndexed { index, tabItem ->
                                Tab(
                                    selected = selectedTabIndex == index,
                                    onClick = { selectedTabIndex = index },
                                    text = { Text(text = tabItem.title) },
                                    selectedContentColor = Color(69, 179, 157),
                                    unselectedContentColor = Color(162, 217, 206)
                                )
                            }
                        }
                        HorizontalPager(state = pagerState) { index ->
                            when (index) {
                                0 -> {
                                    Surface(modifier = Modifier.fillMaxSize()) {
                                        LazyColumn(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            items(FakeData.CHAT_USER_LIST.size) {
                                                ChatUserCard(onTab = {
                                                    controller.navigate(Routes.ChatRoute.route + "/${FakeData.CHAT_USER_LIST[it].name}/${if (FakeData.CHAT_USER_LIST[it].isGroup) R.drawable.ic_group_icon else FakeData.CHAT_USER_LIST[it].icon}")
                                                }, user = FakeData.CHAT_USER_LIST[it])
                                            }
                                        }
                                    }
                                }

                                1 -> {
                                    Surface(modifier = Modifier.fillMaxSize()) {
                                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                            items(FakeData.PEOPLE_LIST) {
                                                PeopleCard(it)
                                            }
                                        }
                                    }
                                }

                                2 -> {
                                    Surface(modifier = Modifier.fillMaxSize()) {
                                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                            items(FakeData.CALL_HISTORY) {
                                                CallCard(model = it)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TabLayout() {

}

data class TabItem(
    val title: String, val selectedIcon: ImageVector, val unSelectedIcon: ImageVector
)

val optionMenu = listOf(
    "New group", "New broadcast", "Linked devices", "Starred Messages", "Payments", "Settings"
)