package com.example.jetpackdesign.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackdesign.R
import com.example.jetpackdesign.data.model.Message

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CustomTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    onIconTab: () -> Unit,
    appIcon: Int,
    iconDescription: String
) {

    CenterAlignedTopAppBar(
        actions = actions,
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = onIconTab
            ) {
                Icon(
                    painter = painterResource(id = appIcon),
                    contentDescription = iconDescription,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        })
}

@Composable
fun CustomMessageCard(message: Message) {
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
                    1.5.dp, MaterialTheme.colorScheme.primary,
                    CircleShape
                )
                .border(3.dp, Color.White, CircleShape)
                .clip(CircleShape)
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

@Preview()
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
                    1.5.dp, MaterialTheme.colorScheme.primary,
                    CircleShape
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
        Text(text = message.user, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = message.timeStamp, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview
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

    var messageContainerColor =
        if (message.user == "me") MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.primaryContainer

    Column() {
        Surface(
            color = messageContainerColor,
            shape = RoundedCornerShape(5f, 30f, 30f, 30f)
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

@Preview
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
        color = color,
        shape = RoundedCornerShape(5f, 30f, 30f, 30f)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "chat_image",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit,
        )
    }
}


@Preview
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

@Preview
@Composable
fun ChatMessageDividerWithTimeTag() {
    Row(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .height(15.dp), verticalAlignment = Alignment.CenterVertically
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
fun ChatUserCard(color: Color,onTab: () -> Unit) {

    Surface(
        color =color
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
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.errorContainer
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
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 0.6.dp,
                color = Color.Gray
            )
        }

    }


}


@Preview
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
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.errorContainer
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