package com.example.jetpackdesign.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackdesign.R
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.model.Message
import com.example.jetpackdesign.data.model.UserChatModel
import com.example.jetpackdesign.util.Constant

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CustomTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    onIconTab: () -> Unit,
    appIcon: Int,
    iconDescription: String,
    isForMainScreen: Boolean = false
) {

    CenterAlignedTopAppBar(actions = actions, title = {
        if (isForMainScreen) {
            Text(text = title)
        } else {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = appIcon),
                    contentDescription = iconDescription,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = title)
            }
        }

    }, navigationIcon = {

        IconButton(
            onClick = onIconTab
        ) {
            Image(
                painter = painterResource(id = if (isForMainScreen) appIcon else R.drawable.ic_back_icon),
                contentDescription = "back icon",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }

    })
}

@Composable
fun CustomMessageCard(message: Message, onUserClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "message_image",
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .size(45.dp)
                .border(
                    1.5.dp, MaterialTheme.colorScheme.primary, CircleShape
                )
                .border(3.dp, Color.White, CircleShape)
                .clip(CircleShape)
                .clickable { onUserClick() }
        )
        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .weight(1f)
        ) {
            MessageNameAndTime(message)
            Spacer(modifier = Modifier.height(10.dp))
            ChatMessageBobble(message)
        }
    }
}

@Composable
fun CustomMessageCardPreview() {
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "message_image",
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .size(45.dp)
                .border(
                    1.5.dp, MaterialTheme.colorScheme.primary, CircleShape
                )
                .border(3.dp, Color.White, CircleShape)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .weight(1f)
        ) {
            MessageNameAndTimePreview()
            Spacer(modifier = Modifier.height(10.dp))
            ChatMessageBobblePreview()
        }
    }
}

@Composable
fun MessageNameAndTime(message: Message) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = message.user.name, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = message.timeStamp, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun MessageNameAndTimePreview() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "welcome", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "08:60 PM", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun ChatMessageBobble(message: Message) {

    val messageContainerColor =
        if (message.user.id == FakeData.CURRENT_USER.id) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer

    Column() {
        Surface(
            color = messageContainerColor, shape = RoundedCornerShape(5f, 30f, 30f, 30f)
        ) {
            Text(
                text = message.message,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (message.image != null) {
            ChatMessageImageBobble(message.image!!, messageContainerColor)
        }
    }

}

@Composable
fun ChatMessageBobblePreview() {

    Column() {
        Surface(
            color = MaterialTheme.colorScheme.errorContainer,
            shape = RoundedCornerShape(5f, 30f, 30f, 30f)
        ) {
            Text(
                text = "welcome to my youtube channel",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        ChatMessageImageBobblePreview()
    }

}

@Composable
fun ChatMessageImageBobble(image: Int, color: Color) {
    Surface(
        color = color, shape = RoundedCornerShape(5f, 30f, 30f, 30f)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "chat_image",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit,
        )
    }
}


@Composable
fun ChatMessageImageBobblePreview() {
    Surface(
        color = MaterialTheme.colorScheme.errorContainer,
        shape = RoundedCornerShape(5f, 30f, 30f, 30f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.sticker),
            contentDescription = "chat_image",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun RowScope.ChatDivider() {
    Divider(
        modifier = Modifier.weight(1f),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}

@Composable
fun ChatMessageDividerWithTimeTag() {
    Row(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .height(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChatDivider()
        Text(
            text = "Today",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        ChatDivider()
    }
}

@Composable
fun ChatUserCard(onTab: () -> Unit, user: UserChatModel) {

    Surface(
        color = MaterialTheme.colorScheme.onPrimary
    ) {
        Column(modifier = Modifier.clickable {
            onTab()
        }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 10.dp),
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp), modifier = Modifier.size(45.dp)
                ) {
                    Image(
                        painter = painterResource(id = if (user.isGroup) R.drawable.ic_group_icon else user.icon),
                        contentDescription = "chat_user_card",
                        modifier = Modifier.size(45.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Row {
                        Text(
                            text = user.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(0.8f)
                        )
                        Text(
                            text = if (user.lastMessage != null) user.lastMessage.timeStamp else "",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.weight(0.2f),
                            textAlign = TextAlign.End
                        )
                    }

                    val message = if (user.lastMessage != null) {

                        if (user.lastMessage.user.id == FakeData.CURRENT_USER.id && !user.isGroup) {
                            "me: ${user.lastMessage.message}"
                        } else if (user.isGroup) {
                            "${user.lastMessage.user.name}: ${user.lastMessage.message}"
                        } else {
                            user.lastMessage.message
                        }

                    } else {
                        ""
                    }

                    Text(text = message, style = MaterialTheme.typography.bodySmall)
                }

            }
        }

    }


}


@Composable
fun ChatUserCardPreview() {

    Surface(
        color = MaterialTheme.colorScheme.errorContainer
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 10.dp),
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.errorContainer
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "chat_user_card",
                    modifier = Modifier.size(45.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Row {
                    Text(
                        text = "Javiya raj",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(0.8f)
                    )
                    Text(
                        text = "08:40 AM",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(0.2f),
                        textAlign = TextAlign.End
                    )
                }

                Text(text = "hi,how are you", style = MaterialTheme.typography.bodySmall)
            }
        }
    }

}

@Composable

fun ChatMessageActionGrid(
    modifier: Modifier = Modifier,
    textFiledValue: TextFieldValue,
    textChange: (TextFieldValue) -> Unit,
    imeSendClick: (Boolean) -> Unit,
    onIconTab: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 5.dp,
        contentColor = MaterialTheme.colorScheme.error,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ) {

        Column(modifier = modifier) {
            Row(
                modifier = modifier
                    .height(65.dp)
                    .fillMaxWidth()
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {

                    val (textFiled, label, sendIcon) = createRefs()

                    BasicTextField(value = textFiledValue,
                        onValueChange = { textChange(it) },
                        maxLines = 2,
                        modifier = Modifier
                            .constrainAs(textFiled) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                bottom.linkTo(parent.bottom, margin = 20.dp)
                            }
                            .width(310.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text, imeAction = ImeAction.Send
                        ),
                        keyboardActions = KeyboardActions(onSend = {
                            imeSendClick(true)
                        })
                    )
                    if (textFiledValue.text.isEmpty()) {
                        Text(text = Constant.MESSAGE_TEXT_HINT,
                            style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                            modifier = Modifier.constrainAs(label) {
                                top.linkTo(parent.top, margin = 20.dp)
                                bottom.linkTo(parent.bottom, margin = 20.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                            })
                    }

                    IconButton(enabled = textFiledValue.text.isNotEmpty(), onClick = {
                        onIconTab()
                    }, modifier = Modifier.constrainAs(sendIcon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "send icon")
                    }
                }

            }
        }


    }
}

@Composable
fun DrawerItem(title: String, icon: Int, onTap: () -> Unit, isActive: Boolean = false) {
    val background =
        if (isActive) Modifier.background(MaterialTheme.colorScheme.primaryContainer) else Modifier

    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .then(background)
            .clickable {
                onTap()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = icon),
            contentDescription = "drawer people icon",
            modifier = Modifier
                .size(35.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                )
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )

    }
}