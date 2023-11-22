package com.example.jetpackdesign.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackdesign.R
import com.example.jetpackdesign.util.Constant

//start and end state of animation for jump to bottom label
private enum class Visibility {
    VISIBLE, GONE
}

@Composable
fun JumpToCard(enabled: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {

    val buttonTransition = updateTransition(
        if (enabled) Visibility.VISIBLE else Visibility.GONE, label = Constant.JUMP_TO_BOTTOM
    )

    //here animateDp use for transition from bottom to top -> return animation values
    val bottomOffset by buttonTransition.animateDp(label = Constant.JUMP_TO_BOTTOM) {
        if (it == Visibility.GONE) {
            (-32).dp
        } else {
            32.dp
        }
    }

    if (bottomOffset > 0.dp) {

        ExtendedFloatingActionButton(
            text = {
                Text(
                    text = Constant.JUMP_TO_BOTTOM,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "top_down_icon",
                    modifier = Modifier.height(20.dp)
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

@Preview
@Composable
fun JumpCardDesign() {
    Surface(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier.size(35.dp),
        color = Color.Black
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_fab_down_arrow_icon),
            contentDescription = "down arrow"
        )
    }
}


