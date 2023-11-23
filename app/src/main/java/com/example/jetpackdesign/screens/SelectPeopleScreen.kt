package com.example.jetpackdesign.screens

import android.os.Looper
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.component.CustomCenterTopBar
import com.example.jetpackdesign.component.FunctionalityDialog
import com.example.jetpackdesign.data.FakeData
import com.valentinilk.shimmer.shimmer
import java.util.logging.Handler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPeopleScreen(controller: NavHostController) {

    var functionalityDialog by remember {
        mutableStateOf(false)
    }

    if (functionalityDialog) {
        FunctionalityDialog {
            functionalityDialog = false
        }
    }

    var isShimmerEffect by remember {
        mutableStateOf(true)
    }

    android.os.Handler(Looper.getMainLooper()).postDelayed({
        isShimmerEffect = false
    }, 3000)

    Scaffold(topBar = {
        CustomCenterTopBar(title = "Select People", onIconTab = {
            controller.popBackStack()
        }, appIcon = R.drawable.ic_back_icon, iconDescription = "app_icon", actions = {
            IconButton(onClick = { functionalityDialog = true }) {
                Icon(Icons.Filled.Search, "people search icon")
            }
        }, isForMainScreen = true
        )
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(FakeData.PEOPLE_LIST) {
                PeopleCard(it, if (isShimmerEffect) Modifier.shimmer() else Modifier)
            }
        }
    }
}