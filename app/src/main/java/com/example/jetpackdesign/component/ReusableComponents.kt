package com.example.jetpackdesign.component

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.jetpackdesign.R
import com.example.jetpackdesign.Routes
import com.example.jetpackdesign.data.FakeData
import com.example.jetpackdesign.data.model.message.Message
import com.example.jetpackdesign.data.model.message.UserChatModel
import com.example.jetpackdesign.util.Constant
import com.example.jetpackdesign.util.Constant.Companion.MONTHS
import com.example.jetpackdesign.util.ModifierConstant.widthModifier
import com.example.jetpackdesign.util.Util
import com.wajahatkarim.flippable.FlipAnimationType
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.FlippableController
import kotlinx.coroutines.launch
import okhttp3.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CustomCenterTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    onIconTab: () -> Unit,
    appIcon: Int,
    iconDescription: String,
    isForMainScreen: Boolean = false,
) {
    CenterAlignedTopAppBar(actions = actions, title = {
        if (isForMainScreen) {
            Text(text = title, style = TextStyle(color = Color(69, 179, 157), fontSize = 25.sp))
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLeftSideTopBar(title: String, actions: @Composable RowScope.() -> Unit = {}) {
    TopAppBar(
        title = {
            Text(
                text = title, style = TextStyle(color = Color(69, 179, 157), fontSize = 25.sp)
            )
        }, actions = actions
    )
}

@Composable
fun CustomMessageCard(message: Message, onUserClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.img),
            contentDescription = "message_image",
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .size(45.dp)
                .border(
                    1.5.dp, MaterialTheme.colorScheme.primary, CircleShape
                )
                .border(3.dp, Color.White, CircleShape)
                .clip(CircleShape)
                .clickable { onUserClick() })
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
            }, verticalAlignment = Alignment.CenterVertically
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

@Composable
fun FlippableCard(
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
    controller: FlippableController,
    modifier: Modifier,
    flipAnimationType: FlipAnimationType,
    flipOnTouch: Boolean = true
) {
    Flippable(
        frontSide = { front() },
        backSide = { back() },
        flipController = controller,
        modifier = modifier,
        flipAnimationType = flipAnimationType,
        flipOnTouch = flipOnTouch
    )
}

@Composable
fun CustomTextString() {

    val colors1 = listOf(Color(0xFF9CCC65), Color(0xFFF5CBA7), Color(0xFF48C9B0))
    val colors2 = listOf(Color(0XFFCB4303), Color(0xFFA569BD), Color(0xFFF4D03F))

    Text(text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                Brush.linearGradient(colors1), fontSize = 20.sp
            )
        ) {
            append("J")
        }
        withStyle(
            SpanStyle(
                fontSize = 15.sp,
                brush = Brush.linearGradient(colors2),
            )
        ) {
            append("etpack")
        }

        withStyle(
            SpanStyle(
                Brush.linearGradient(colors1), fontSize = 20.sp
            )
        ) {
            append(" C")
        }
        withStyle(
            SpanStyle(
                fontSize = 15.sp,
                brush = Brush.linearGradient(colors2),
            )
        ) {
            append("ompose")
        }


    })
}

@Composable
fun GradientText() {

    val colors = listOf(Color(0xFF9CCC65), Color(0xFFF5CBA7), Color(0xFF48C9B0))

    Text(
        text = "Custom Text",
        style = MaterialTheme.typography.titleLarge.copy(
            brush = Brush.linearGradient(colors), fontSize = 30.sp
        ),
    )

}

@Composable
fun AnimatedText() {

    var count by remember {
        mutableIntStateOf(0)
    }

    var animationType by remember {
        mutableStateOf(AnimatedContentTransitionScope.SlideDirection.Up)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        val colors = listOf(Color(0XFFCB4303), Color(0xFFA569BD), Color(0xFFF4D03F))

        IconButton(onClick = {
            if (count > 0) {
                animationType = AnimatedContentTransitionScope.SlideDirection.Down
                count--
            }
        }) {
            Icon(Icons.Default.KeyboardArrowLeft, "down_icon", modifier = Modifier.size(30.dp))
        }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                slideIntoContainer(
                    towards = animationType, animationSpec = tween(durationMillis = 200)
                ) togetherWith ExitTransition.None
            },
            label = "",
        ) {
            Text(
                text = "$it",
                style = MaterialTheme.typography.titleLarge.copy(brush = Brush.linearGradient(colors = colors)),
            )
        }
        IconButton(onClick = {
            animationType = AnimatedContentTransitionScope.SlideDirection.Up
            count++
        }) {
            Icon(Icons.Default.KeyboardArrowRight, "up_icon", modifier = Modifier.size(30.dp))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ExposedDropdownMenuSample() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Label") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(
                            selectionOption, modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCalender() {

    val listOfDaysTitle = listOf("Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    val currentMonth = Util.getMonth()
    val currentYear = Util.getYear()
    val currentDay = Util.getDay()

    var month by remember {
        mutableIntStateOf(currentMonth)
    }

    val totalDaysCount by remember {
        derivedStateOf {
            Util.getCurrentMonthDays(year = currentYear, month = month)
        }
    }

    var year by remember {
        mutableIntStateOf(currentYear)
    }
    val disableDayCount by remember {
        derivedStateOf {
            listOfDaysTitle.indexOf(Util.getFirstDayOfMonth(year, month))
        }
    }


    val calenderTitle by remember {
        derivedStateOf {
            "${MONTHS[month - 1]} $year"
        }
    }

    var animationType by remember {
        mutableStateOf(AnimatedContentTransitionScope.SlideDirection.Up)
    }

    val pagerState = rememberPagerState(initialPage = month - 1) {
        12
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        when (pagerState.currentPage) {
            12 -> {
                Log.e("TAG", "CustomCalender: month-1 and year ${year++}")
            }

            1 -> {
                Log.e("TAG", "CustomCalender: month-12 and year ${year--}")
            }

            else -> {
                Log.e("TAG", "CustomCalender: month-${pagerState.currentPage + 1}")
            }
        }
        //month = pagerState.currentPage + 1
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                animationType = AnimatedContentTransitionScope.SlideDirection.Down
                if (month == 1) {
                    month = 12
                    year -= 1
                } else {
                    month -= 1
                }
            }) {
                Icon(Icons.Default.KeyboardArrowLeft, "down_icon", modifier = Modifier.size(30.dp))
            }
            AnimatedContent(
                targetState = calenderTitle,
                transitionSpec = {
                    slideIntoContainer(
                        towards = animationType,
                        animationSpec = tween(durationMillis = 200)
                    ) togetherWith ExitTransition.None
                },
                modifier = Modifier.width(200.dp),
                label = "",
            ) {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = widthModifier,
                    textAlign = TextAlign.Center
                )
            }
            IconButton(onClick = {
                animationType = AnimatedContentTransitionScope.SlideDirection.Up

                if (month == 12) {
                    year += 1
                    month = 1
                } else {
                    month += 1
                }

            }) {
                Icon(Icons.Default.KeyboardArrowRight, "up_icon", modifier = Modifier.size(30.dp))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(listOfDaysTitle.size) {
                Text(
                    text = listOfDaysTitle[it],
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        /*
        HorizontalPager(state = pagerState, modifier = widthModifier) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 7),
                content = {
                    items(disableDayCount) {
                        Surface {

                        }
                    }
                    items(totalDaysCount) {
                        val dayValue = it + 1
                        DayCard(
                            day = dayValue.toString(),
                            isCurrentDay = currentMonth == month && dayValue == currentDay,
                            isAnyEvent = it == 15,
                            onTap = {

                            }
                        )

                    }
                },
                contentPadding = PaddingValues(3.dp),
            )
        }

         */

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 7),
            content = {
                items(disableDayCount) {
                    Surface {

                    }
                }
                items(totalDaysCount) {
                    val dayValue = it + 1
                    DayCard(
                        day = dayValue.toString(),
                        isCurrentDay = currentMonth == month && dayValue == currentDay,
                        isAnyEvent = it == 15,
                        onTap = {

                        }
                    )

                }
            },
            contentPadding = PaddingValues(3.dp),
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Event",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(modifier = widthModifier) {
            items(15) {
                CalenderEventCard()
            }
        }
    }
}


@Composable
fun DayCard(
    modifier: Modifier = Modifier,
    day: String,
    isCurrentDay: Boolean = false,
    isAnyEvent: Boolean = false,
    onTap: () -> Unit = {},
) {

    val colors = listOf(Color(0XFFCB4303), Color(0xFFA569BD), Color(0xFFF4D03F))

    val customBorder = if (isCurrentDay) {
        BorderStroke(2.dp, brush = Brush.linearGradient(colors))
    } else {
        BorderStroke(0.dp, brush = Brush.linearGradient(colors))
    }


    Surface(
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colorScheme.errorContainer,
        modifier = modifier
            .height(60.dp)
            .padding(2.5.dp)
            .clickable { onTap() },
        border = customBorder,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .then(Modifier.padding(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {
            Text(
                text = day,
                style = MaterialTheme.typography.titleMedium,

                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.6f)
            )
            if (isAnyEvent) {
                Surface(
                    modifier = Modifier
                        .size(10.dp),
                    shape = CircleShape,
                    color = Color.Gray
                ) {

                }
            }
        }
    }
}


@Composable
fun CalenderEventCard() {
    Surface(
        modifier = widthModifier.padding(vertical = 2.5.dp, horizontal = 3.dp),
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colorScheme.errorContainer
    ) {
        Row(modifier = widthModifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
            Column(modifier = widthModifier.weight(0.9f)) {
                Text(text = "Project Review", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "21-03-2023", style = MaterialTheme.typography.titleSmall)
            }
            IconButton(onClick = {}, modifier = Modifier.weight(0.1f)) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more_button")
            }
        }
    }
}

@Composable
fun DayCardView(
    modifier: Modifier = Modifier,
    day: String,
    isCurrentDay: Boolean = false,
    isAnyEvent: Boolean = false,
    onTap: () -> Unit = {},
) {

    val colors = listOf(Color(0XFFCB4303), Color(0xFFA569BD), Color(0xFFF4D03F))

    val customBorder = if (isCurrentDay) {
        BorderStroke(2.dp, brush = Brush.linearGradient(colors))
    } else {
        BorderStroke(0.dp, brush = Brush.linearGradient(colors))
    }


    Surface(
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colorScheme.errorContainer,
        modifier = modifier
            .height(30.dp)
            .padding(2.5.dp)
            .clickable { onTap() },
        border = customBorder,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .then(Modifier.padding(10.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {
            Text(
                text = day,
                style = MaterialTheme.typography.titleSmall,

                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.6f)
            )
            if (isAnyEvent) {
                Surface(
                    modifier = Modifier
                        .size(10.dp),
                    shape = CircleShape,
                    color = Color.Gray
                ) {

                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainMonthView(navHostController: NavHostController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val listOfDaysTitle = listOf("Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

        items(MONTHS.size) {
            Surface(modifier = Modifier.padding(horizontal = 5.dp).height(150.dp).clickable {
                navHostController.navigate(Routes.Test1Route.route)
            }) {
                Column(modifier = widthModifier) {

                    val month = it + 1
                    val disableCount=listOfDaysTitle.indexOf(Util.getFirstDayOfMonth(Util.getYear(), month))
                    val dayCount=Util.getCurrentMonthDays(Util.getYear(),month)

                    Text(text = MONTHS[it], style = MaterialTheme.typography.titleLarge, color =if(Util.getMonth()==month) Color.Blue else Color.Black)


                    LazyVerticalGrid(
                        columns = GridCells.Fixed(count = 7),
                        content = {
                            items(disableCount) {
                                Surface {

                                }
                            }
                            items(dayCount) {
                                val dayValue = it + 1
                                val isCurrentDay=Util.getDay()==dayValue && Util.getMonth()==month
                                if(isCurrentDay){
                                    Surface(modifier = Modifier
                                        .width(30.dp)
                                        .height(20.dp), shape = CircleShape, color = MaterialTheme.colorScheme.primary) {
                                        Text(text = dayValue.toString(), textAlign = TextAlign.Center)
                                    }
                                }else{
                                    Text(text = dayValue.toString(), textAlign = TextAlign.Center)
                                }

                            }
                        },
                        contentPadding = PaddingValues(3.dp),
                        modifier = Modifier.height(150.dp)
                    )
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    actions: @Composable RowScope.() -> Unit
) {
    TopAppBar(title = { Text(text = title, style = MaterialTheme.typography.titleLarge) }, actions = actions)
}