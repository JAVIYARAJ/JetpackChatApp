package com.example.jetpackdesign.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private enum class Visibility {
    VISIBLE, GONE
}


@Composable
fun JumpToCard(enabled: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {

    val buttonTransition = updateTransition(
        if (enabled) Visibility.VISIBLE else Visibility.GONE, label = "jump to bottom"
    )

    val bottomOffset by buttonTransition.animateDp(label = "jump to bottom") {
        if (it == Visibility.GONE) {
            (-32).dp
        } else {
            32.dp
        }
    }

    if (bottomOffset > 0.dp) {
        ExtendedFloatingActionButton(
            text = { Text(text = "Jump to bottom") },
            icon = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "top_down_icon"
                )
            },
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            modifier = modifier
                .offset(x = 0.dp, y = -bottomOffset)
                .height(36.dp)
        )
    }

}

@Preview
@Composable
fun JumpToBottomPreview() {
    JumpToCard(enabled = true, onClick = {})
}

