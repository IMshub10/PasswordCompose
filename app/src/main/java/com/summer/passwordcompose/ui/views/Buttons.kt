package com.summer.passwordcompose.ui.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.summer.passwordcompose.R
import com.summer.passwordcompose.ui.theme.PasswordComposeTheme
import com.summer.passwordcompose.ui.theme.normalIconSize
import com.summer.passwordcompose.ui.theme.spacingLarge
import com.summer.passwordcompose.ui.theme.spacingSmall
import com.summer.passwordcompose.ui.theme.spacingXMedium
import com.summer.passwordcompose.ui.theme.spacingXSmall
import com.summer.passwordcompose.utils.preview.DarkLightPreviews


@Composable
fun TextImageButton(
    text: String,
    modifier: Modifier,
    borderWidth: Dp = 1.dp,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(spacingLarge)
    Card(
        shape = shape,
        modifier = modifier
            .padding(spacingXSmall)
            .border(borderWidth, borderColor, CircleShape)
            .clip(shape)
            .indication(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.Green,
                    radius = spacingLarge,
                )
            )
            .clickable(
                onClick = onClick,
            )
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(spacingSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_reset),
                contentDescription = "icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = spacingXSmall, end = spacingXSmall)
                    .size(normalIconSize)
            )
            Text(
                text = text,
                modifier = Modifier.padding(end = spacingXMedium),
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@DarkLightPreviews
@Composable
fun GreetingPreview() {
    PasswordComposeTheme {
        TextImageButton(
            "Reset", Modifier
                .wrapContentSize(align = Alignment.TopCenter), onClick = {

            }
        )
    }
}
