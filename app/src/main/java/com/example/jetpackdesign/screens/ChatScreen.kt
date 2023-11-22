package com.example.jetpackdesign.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.Routes
import com.example.jetpackdesign.component.ChatMessageActionGrid
import com.example.jetpackdesign.component.ChatMessageDividerWithTimeTag
import com.example.jetpackdesign.component.CustomMessageCard
import com.example.jetpackdesign.component.CustomTopBar
import com.example.jetpackdesign.component.FunctionalityDialog
import com.example.jetpackdesign.component.JumpToCard
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.model.Message
import com.example.jetpackdesign.data.state.MessageState
import com.example.jetpackdesign.util.Constant
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(name: String, icon: Int, controller: NavHostController) {

    val scope = rememberCoroutineScope()

    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = FakeData.MESSAGES.size)
    val chatState = MessageState(initialMessages = FakeData.MESSAGES)

    var functionalityDialogShow by remember {
        mutableStateOf(false)
    }

    if (functionalityDialogShow) {
        FunctionalityDialog {
            functionalityDialogShow = false
        }
    }

    Scaffold(topBar = {
        CustomTopBar(
            title = name,
            actions = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search_icon),
                    contentDescription = "search icon",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(
                            CircleShape
                        )
                        .clickable {
                            functionalityDialogShow = true
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_information_icon),
                    contentDescription = "info icon",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            CircleShape
                        )
                        .clickable {
                            functionalityDialogShow = true
                        }
                )

            },
            onIconTab = {
                controller.popBackStack()
            },
            appIcon = icon,
            iconDescription = "app_icon",
        )
    }) { paddingValue ->

        var chatMessageValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {

            Box(modifier = Modifier.weight(1f)) {
                LazyColumn(state = scrollState, modifier = Modifier.fillMaxWidth()) {
                    items(chatState.messages.size) { index ->
                        if (index == 5) {
                            ChatMessageDividerWithTimeTag()
                        }
                        CustomMessageCard(chatState.messages[index], onUserClick = {
                            controller.navigate(Routes.ProfileRoute.route + "/${chatState.messages[index].user.id}/${chatState.messages[index].user.name}/${chatState.messages[index].user.email}/${chatState.messages[index].user.description}/${chatState.messages[index].user.image}")
                        })
                    }
                }

                //LocalDensity helps with conversions between pixels and density-independent pixels.
                val jumpThreshold = with(LocalDensity.current) {
                    Constant.JUMP_BUTTON_THRESHOLD.toPx()
                }

                val jumpToButtonEnabled by remember {
                    //It is used to create a derived state based on one or more other state values.
                    derivedStateOf {
                        scrollState.firstVisibleItemIndex != 0 || scrollState.firstVisibleItemScrollOffset > jumpThreshold
                    }
                }

                JumpToCard(enabled = !jumpToButtonEnabled, onClick = {
                    scope.launch {
                        scrollState.animateScrollToItem(FakeData.MESSAGES.size)
                    }
                }, modifier = Modifier.align(Alignment.BottomCenter))
            }
            ChatMessageActionGrid(
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding(),
                textFiledValue = chatMessageValue,
                textChange = { value ->
                    chatMessageValue = value
                }, onIconTab = {
                    chatState.addMessages(
                        Message(
                            message = chatMessageValue.text,
                            user = FakeData.CURRENT_USER,
                            timeStamp = "now"
                        )
                    )
                    scope.launch {
                        scrollState.animateScrollToItem(chatState.messages.size)
                    }
                    chatMessageValue = TextFieldValue("")

                },
                imeSendClick = {
                    if (it) {
                        chatState.addMessages(
                            Message(
                                message = chatMessageValue.text,
                                user = FakeData.CURRENT_USER,
                                timeStamp = "now"
                            )
                        )
                        scope.launch {
                            scrollState.animateScrollToItem(chatState.messages.size)
                        }
                        chatMessageValue = TextFieldValue("")
                    }
                }
            )
        }

    }
}

