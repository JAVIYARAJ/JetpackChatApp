package com.example.jetpackdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackdesign.component.ChatMessageDividerWithTimeTag
import com.example.jetpackdesign.component.CustomMessageCard
import com.example.jetpackdesign.component.CustomTopBar
import com.example.jetpackdesign.component.JumpToCard
import com.example.jetpackdesign.data.model.Message
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComponent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainComponent() {
    Scaffold(topBar = {
        CustomTopBar(
            title = "Jetpack Compose",
            actions = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search_icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                )
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "info_icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                )
            },
            onIconTab = { },
            appIcon = R.drawable.ic_jetchat_front,
            iconDescription = "app_icon"
        )
    }) {

        val messages = listOf(
            Message(message = "hi", "me", timeSTamp = "08:10 PM"),
            Message(message = "hello", "me", timeSTamp = "08:10 PM"),
            Message(message = "hi", "javiya meet", timeSTamp = "08:10 PM"),
            Message(
                message = "sample image",
                "javiya meet",
                timeSTamp = "08:10 PM",
                image = R.drawable.sticker
            ),
            Message(message = "good", "me", timeSTamp = "08:10 PM"),
            Message(message = "okay", "me", timeSTamp = "08:10 PM")
        )

        LazyColumn(modifier = Modifier.padding(it)) {
            items(messages.size) { index ->
                if (index == 5) {
                    ChatMessageDividerWithTimeTag()
                }
                CustomMessageCard(messages[index])
            }
        }
    }
}

private val JumpToBottomThreshold = 56.dp
