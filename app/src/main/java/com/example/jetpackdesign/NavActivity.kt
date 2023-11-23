package com.example.jetpackdesign

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.jetpackdesign.component.CustomCenterTopBar
import com.example.jetpackdesign.component.DrawerItem
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.state.DrawerItemState
import com.example.jetpackdesign.databinding.ActivityNavBinding
import com.example.jetpackdesign.util.Constant
import kotlinx.coroutines.launch

class NavActivity : AppCompatActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ComposeView(this).apply {
            consumeWindowInsets = false
            setContent {

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

                                                    }

                                                    Constant.CHAT -> {

                                                    }

                                                    Constant.CALLS -> {

                                                    }

                                                    Constant.SETTINGS -> {

                                                    }

                                                    Constant.PEOPLE -> {

                                                    }

                                                    Constant.LOGOUT -> {

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
                            CustomCenterTopBar(
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
                    ) {
                        AndroidViewBinding(ActivityNavBinding::inflate)
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp() || super.onSupportNavigateUp()
    }

    private fun findNavController(): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHost.navController
    }

}