package com.summer.passwordcompose.presentation.common

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.summer.passwordcompose.R
import com.summer.passwordcompose.ui.theme.PasswordComposeTheme
import com.summer.passwordcompose.ui.theme.spacingSmall
import com.summer.passwordcompose.utils.preview.DarkLightPreviews
import java.lang.RuntimeException

/*
private object RedRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() =
        RippleTheme.defaultRippleColor(
            Color.Red,
            lightTheme = true
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Transparent,
            lightTheme = true
        )
}*/

@Composable
fun PinText(
    modifier: Modifier,
    rippleRadius: Dp,
    char: String, rippleColor: Color, onClick: () -> Unit
) {
    val interactionSource = MutableInteractionSource()
    val ripple = rememberRipple(
        color = rippleColor,
        radius = rippleRadius,
        bounded = true
    )
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple,
                onClick = onClick
            )
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center), text = char, fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Composable
fun PinButton(
    modifier: Modifier,
    rippleRadius: Dp,
    icon: Painter, rippleColor: Color, onClick: () -> Unit
) {
    val interactionSource = MutableInteractionSource()
    val ripple = rememberRipple(
        color = rippleColor,
        radius = rippleRadius,
        bounded = true
    )
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple,
                onClick = onClick,
            )
    ) {
        Icon(
            painter = icon,
            contentDescription = "clear",
            tint = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun PinLockView(
    length: Int = 4,
    filledColor: Color,
    unfilled: Color, onClick: (value: String) -> Unit, onClearClick: () -> Unit
) {
    val pinText = remember { mutableStateOf("") }

    fun updatePinText(additionText: String) {
        if (pinText.value.length < length) {
            pinText.value = pinText.value + additionText
            onClick(additionText).toString()
        }
    }

    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dp
    val height = configuration.screenHeightDp.dp
    val minSize = min(width, height)
    val sideWidth = minSize / 3
    val sideHeight = (sideWidth.value * 0.8).dp
    val rippleRadius = ((sideWidth.value / 2) * 0.75).toInt()

    val heightModifier = Modifier.height(sideHeight)
    val widthModifier = Modifier.width(sideWidth)
    val heightWidthModifier = heightModifier.width(sideWidth)

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(spacingSmall))
        Dots(
            length = length,
            atLength = pinText.value.length,
            filledColor = filledColor,
            unfilled = unfilled
        )
        for (row in 0..2) {
            Row(
                modifier = heightModifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (item in 1..3) {
                    PinText(
                        modifier = widthModifier
                            .clip(CircleShape),
                        rippleRadius = rippleRadius.dp,
                        ((row * 3) + item).toString(),
                        rippleColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        updatePinText(((row * 3) + item).toString())
                    }
                }
            }
        }
        Row(
            modifier = heightModifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = heightWidthModifier
            )
            PinText(
                modifier = widthModifier
                    .clip(CircleShape),
                rippleRadius = rippleRadius.dp,
                "0",
                rippleColor = MaterialTheme.colorScheme.onPrimary
            ) {
                updatePinText(("0").toString())
            }
            PinButton(
                modifier = heightWidthModifier
                    .clip(CircleShape),
                rippleRadius = rippleRadius.dp,
                icon = painterResource(id = R.drawable.ic_back_delete),
                rippleColor = MaterialTheme.colorScheme.onPrimary
            ) {
                pinText.value = ""
                onClearClick()
            }
        }
    }
}

@Composable
fun Dots(length: Int = 4, atLength: Int = 4, filledColor: Color, unfilled: Color) {
    if (atLength > length) {
        throw RuntimeException("Length of pinText is greater than specified")
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .align(Alignment.Center)
        ) {
            for (i in 1..length) {
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .height(
                            8.dp
                        )
                        .clip(CircleShape)
                        .background(if (i <= atLength) filledColor else unfilled)
                )
                if (i < length) {
                    Spacer(
                        modifier = Modifier
                            .width(32.dp)
                    )
                }
            }
        }
    }
}


@DarkLightPreviews
@Composable
fun PinLockViewPreview(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val enterPin = remember { mutableStateOf("") }
    PasswordComposeTheme {
        Box {

            PinLockView(
                length = 4,
                filledColor = MaterialTheme.colorScheme.onSecondary,
                unfilled = MaterialTheme.colorScheme.onTertiary,
                onClick = {
                    enterPin.value = enterPin.value + it
                },
                onClearClick = {
                    Toast.makeText(context, enterPin.value, Toast.LENGTH_SHORT).show()
                    enterPin.value = ""
                }
            )
        }
    }
}