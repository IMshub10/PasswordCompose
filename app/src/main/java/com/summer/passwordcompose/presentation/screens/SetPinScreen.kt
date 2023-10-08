@file:OptIn(ExperimentalComposeUiApi::class)

package com.summer.passwordcompose.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.summer.passwordcompose.R
import com.summer.passwordcompose.presentation.common.PinInput
import com.summer.passwordcompose.presentation.navigation.BottomNavItem
import com.summer.passwordcompose.ui.theme.PasswordComposeTheme
import com.summer.passwordcompose.ui.theme.White
import com.summer.passwordcompose.ui.theme.spacingAtom
import com.summer.passwordcompose.ui.theme.spacingLarge
import com.summer.passwordcompose.ui.theme.spacingMedium
import com.summer.passwordcompose.ui.theme.spacingPinView
import com.summer.passwordcompose.ui.theme.spacingSmall
import com.summer.passwordcompose.ui.theme.spacingXLarge
import com.summer.passwordcompose.ui.theme.spacingXSmall
import com.summer.passwordcompose.utils.preview.DarkLightPreviews

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SetPinScreen(modifier: Modifier = Modifier, keyboardController: SoftwareKeyboardController) {

    val enterPin = remember { mutableStateOf("") }
    val reEnterPin = remember { mutableStateOf("") }

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacingSmall),
                text = LocalContext.current.getString(R.string.set_pin),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacingSmall),
                text = LocalContext.current.getString(R.string.please_set_your_pin),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Text(
                modifier = modifier.padding(
                    start = spacingSmall,
                    end = spacingSmall,
                    top = spacingXLarge,
                    bottom = spacingXSmall
                ),
                text = LocalContext.current.getString(R.string.enter_pin),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
            PinInput(
                value = enterPin.value,
                modifier = modifier.padding(top = spacingSmall),
                onValueChanged = {
                    enterPin.value = it
                },
                length = 4,
                obscureText = null,
                cellModifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(spacingXSmall)
                    )
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
                        shape = RoundedCornerShape(spacingXSmall)
                    )
                    .size(width = spacingPinView, height = spacingPinView)
                    .clip(MaterialTheme.shapes.large)
            )
            Text(
                modifier = modifier.padding(
                    start = spacingSmall,
                    end = spacingSmall,
                    top = spacingMedium,
                    bottom = spacingXSmall
                ),
                text = LocalContext.current.getString(R.string.re_enter_pin),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
            PinInput(
                value = reEnterPin.value,
                modifier = modifier.padding(top = spacingSmall),
                onValueChanged = {
                    reEnterPin.value = it
                },
                obscureText = null,
                length = 4,
                cellModifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(spacingXSmall)
                    )
                    .border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary),
                        shape = RoundedCornerShape(spacingXSmall)
                    )
                    .size(width = spacingPinView, height = spacingPinView)
                    .clip(MaterialTheme.shapes.large)
            )
            Button(modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacingSmall, vertical = spacingSmall), onClick = {
                keyboardController.hide()
                //navController.navigate(BottomNavItem.SetPin.fullRoute)
            }) {
                Text(
                    modifier = modifier.padding(vertical = spacingAtom),
                    text = LocalContext.current.getString(R.string.next),
                    color = White
                )
            }
        }
    }
}

@DarkLightPreviews
@Composable
fun SetPinScreenPreview() {
    PasswordComposeTheme {
        SetPinScreen(
            //navController = rememberAnimatedNavController(),
            keyboardController = LocalSoftwareKeyboardController.current!!
        )
    }
}