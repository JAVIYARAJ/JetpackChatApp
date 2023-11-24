package com.example.jetpackdesign.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackdesign.R
import com.example.jetpackdesign.component.CustomCenterTopBar
import com.example.jetpackdesign.component.FlippableCard
import com.example.jetpackdesign.component.FunctionalityDialog
import com.example.jetpackdesign.data.model.profile.ProfileItemModel
import com.wajahatkarim.flippable.FlipAnimationType
import com.wajahatkarim.flippable.rememberFlipController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(name: String, description: String, email: String, image: Int) {

    var functionalityDialog by remember {
        mutableStateOf(false)
    }

    if (functionalityDialog) {
        FunctionalityDialog {
            functionalityDialog = false
        }
    }

    val controller = rememberFlipController()

    val profileItems = listOf(
        ProfileItemModel(label = "Email", title = email, isLink = true),
        ProfileItemModel(label = "Country", title = "India"),
        ProfileItemModel(label = "Language", title = "English", isLast = true),
    )

    var isProfileActive by remember {
        mutableStateOf(false)
    }

    Scaffold(topBar = {
        CustomCenterTopBar(
            title = "Profile",
            actions = {
                IconButton(onClick = {
                    functionalityDialog = true
                }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "˳")
                }
            },
            onIconTab = {
            },
            appIcon = R.drawable.ic_app_icon,
            iconDescription = "profile icon",
            isForMainScreen = true
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            val defaultWidgetMargin = 10.dp

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                val (imageKey, usernameKey, descriptionKey, profileItemsKey) = createRefs()

                FlippableCard(
                    front = { FlippableFrontWidget(image = image) },
                    back = { FlippableBackWidget(image) },
                    controller = controller,
                    modifier = Modifier.constrainAs(imageKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = defaultWidgetMargin)
                    },
                    flipAnimationType = FlipAnimationType.HORIZONTAL_ANTI_CLOCKWISE,
                    flipOnTouch = false
                )
                Text(text = name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.constrainAs(usernameKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(imageKey.bottom, margin = defaultWidgetMargin)
                    })
                Text(text = description,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.constrainAs(descriptionKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(usernameKey.bottom, margin = 10.dp)
                    })
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(profileItemsKey) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(descriptionKey.bottom, margin = 10.dp)
                    }) {

                    items(profileItems) { value ->
                        ProfileItem(
                            label = value.label,
                            title = value.title,
                            isLink = value.isLink,
                        )
                    }
                    //single switch item
                    item {
                        ConstraintLayout(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .clickable { }) {
                            val (descriptionInnerKey, statusLabel, status) = createRefs()

                            Text(text = "Status",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.constrainAs(statusLabel) {
                                    start.linkTo(
                                        parent.start
                                    )
                                    top.linkTo(descriptionInnerKey.bottom, margin = 20.dp)
                                })

                            Switch(checked = isProfileActive, onCheckedChange = { value ->
                                isProfileActive = value
                                controller.flip()
                            }, modifier = Modifier.constrainAs(status) {
                                start.linkTo(
                                    parent.start
                                )
                                top.linkTo(statusLabel.bottom)
                            })
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun FlippableFrontWidget(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "profile icon",
        modifier = Modifier
            .size(200.dp)
            .clip(
                CircleShape
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun FlippableBackWidget(image: Int) {
    ConstraintLayout {
        val (innerImageKey, verifiedKey) = createRefs()
        Image(
            painter = painterResource(id = image),
            contentDescription = "profile icon",
            modifier = Modifier
                .size(200.dp)
                .clip(
                    CircleShape
                )
                .constrainAs(innerImageKey) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop
        )
        Image(painter = painterResource(id = R.drawable.ic_verified_icon),
            contentDescription = "app icon",
            modifier = Modifier
                .size(35.dp)
                .constrainAs(verifiedKey) {
                    start.linkTo(innerImageKey.end, margin = Dp(-45f))
                    bottom.linkTo(innerImageKey.bottom, margin = 5.dp)
                }
                .clip(
                    CircleShape
                ),
            contentScale = ContentScale.Fit)

    }
}

@Composable
fun ProfileItem(
    label: String,
    title: String,
    isLink: Boolean = false,
    onTap: () -> Unit = {},
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clickable { onTap() }) {
        val (description, statusLabel, status, divider1) = createRefs()
        Text(text = label,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(statusLabel) {
                start.linkTo(
                    parent.start
                )
                top.linkTo(description.bottom, margin = 20.dp)
            })

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.constrainAs(status) {
                start.linkTo(
                    parent.start
                )
                top.linkTo(statusLabel.bottom)
            },
            color = if (isLink) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Divider(modifier = Modifier.constrainAs(divider1) {
            start.linkTo(
                parent.start
            )
            end.linkTo(parent.end)
            top.linkTo(status.bottom, margin = 5.dp)
        })

    }
}

