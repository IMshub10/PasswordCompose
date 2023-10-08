package com.summer.passwordcompose.presentation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.summer.passwordcompose.R
import com.summer.passwordcompose.presentation.common.OutLinedEditText
import com.summer.passwordcompose.presentation.navigation.BottomNavItem
import com.summer.passwordcompose.ui.theme.PasswordComposeTheme
import com.summer.passwordcompose.ui.theme.White
import com.summer.passwordcompose.ui.theme.spacingAtom
import com.summer.passwordcompose.ui.theme.spacingLarge
import com.summer.passwordcompose.ui.theme.spacingSmall
import com.summer.passwordcompose.ui.theme.spacingXSmall
import com.summer.passwordcompose.utils.preview.DarkLightPreviews

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    keyboardController: SoftwareKeyboardController
) {
    val cont = LocalContext.current

    var nameValue: String? by remember { mutableStateOf("") }
    var mobileValue: String? by remember { mutableStateOf("") }

    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier.padding(horizontal = spacingSmall),
                text = LocalContext.current.getString(R.string.create_account),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                modifier = modifier.padding(horizontal = spacingSmall),
                text = LocalContext.current.getString(R.string.create_your_account_to_continue),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            Box(
                modifier = modifier.padding(
                    start = spacingSmall,
                    end = spacingSmall,
                    top = spacingLarge,
                    bottom = spacingXSmall
                )
            ) {
                OutLinedEditText(
                    startIcon = Icons.Default.Person,
                    hint = cont.getString(R.string.name),
                    required = true
                ) {
                    nameValue = it
                }
            }

            Box(
                modifier = modifier.padding(
                    start = spacingSmall,
                    end = spacingSmall,
                    top = spacingXSmall,
                    bottom = spacingLarge
                )
            ) {
                OutLinedEditText(
                    startIcon = Icons.Default.Phone,
                    hint = cont.getString(R.string.mobile)
                ) {
                    mobileValue = it
                }
            }
            Button(modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacingSmall, vertical = spacingSmall), onClick = {
                keyboardController.hide()
                navController.navigate(BottomNavItem.SetPin.fullRoute)
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


@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@DarkLightPreviews
@Composable
fun SignUpScreenPreview() {
    PasswordComposeTheme {
        SignUpScreen(
            navController = rememberAnimatedNavController(),
            keyboardController = LocalSoftwareKeyboardController.current!!
        )
    }
}