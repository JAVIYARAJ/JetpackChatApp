package com.example.jetpackdesign.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.component.CustomCenterTopBar
import com.example.jetpackdesign.component.FunctionalityDialog
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.model.call.CallFeature
import com.example.jetpackdesign.data.model.call.CallHistoryModel
import com.example.jetpackdesign.data.model.call.CallType

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CallScreen(controller: NavHostController) {

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
            CustomCenterTopBar(
                title = "Calls",
                onIconTab = {
                    controller.popBackStack()
                },
                appIcon = R.drawable.ic_back_icon,
                iconDescription = "app_icon",
                actions = {
                    IconButton(onClick = {
                        functionalityDialog = true
                    }) {
                        Icon(Icons.Filled.Search, "call search icon")
                    }
                },
                isForMainScreen = true
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(FakeData.CALL_HISTORY) {
                CallCard(model = it)
            }
        }
    }
}

@Composable
fun CallCard(model: CallHistoryModel) {

    Surface(
        color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val callColor = when (model.callType) {
                CallType.Outgoing -> Color.Green
                CallType.Cancelled -> Color.Red
                CallType.Incoming -> Color.Black

            }

            val callIcon = when (model.callType) {
                CallType.Outgoing -> R.drawable.ic_upward_arrow_icon
                CallType.Cancelled -> R.drawable.ic_down_arrow_icon
                CallType.Incoming -> R.drawable.ic_down_arrow_icon
            }

            val callFeatureIcon = when (model.callFeature) {
                CallFeature.Video -> R.drawable.ic_video_camera_icon
                CallFeature.Audio -> R.drawable.ic_phone_call_icon
            }

            Image(
                painter = painterResource(id = model.user.image),
                contentDescription = "chat_user_card",
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = model.user.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = callColor
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = callIcon),
                        contentDescription = "down icon",
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = model.timestamp,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

            }
            Image(
                painter = painterResource(id = callFeatureIcon),
                contentDescription = "call icon"
            )
        }
    }


}