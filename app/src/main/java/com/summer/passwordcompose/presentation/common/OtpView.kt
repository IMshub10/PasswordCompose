package com.summer.passwordcompose.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.summer.passwordcompose.ui.theme.PasswordComposeTheme
import com.summer.passwordcompose.ui.theme.spacingSmall
import com.summer.passwordcompose.ui.theme.spacingXSmall
import com.summer.passwordcompose.utils.preview.DarkLightPreviews
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun OtpCell(
    modifier: Modifier,
    value: Char?,
    isCursorVisible: Boolean = false,
    obscureText: String?
) {
    val scope = rememberCoroutineScope()
    val (cursorSymbol, setCursorSymbol) = remember { mutableStateOf("") }

    LaunchedEffect(key1 = cursorSymbol, isCursorVisible) {
        if (isCursorVisible) {
            scope.launch {
                delay(400)
                setCursorSymbol(if (cursorSymbol.isEmpty()) "|" else "")
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        Text(
            text = if (isCursorVisible) cursorSymbol else if (!obscureText.isNullOrBlank() && value?.toString()
                    .isNullOrBlank().not()
            ) obscureText else value?.toString() ?: "",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
/**
 * @param obscureText Set null, if want to show the number.
 */
@Composable
fun PinInput(
    modifier: Modifier = Modifier,
    cellModifier: Modifier = Modifier,
    length: Int = 5,
    value: String = "",
    disableKeypad: Boolean = false,
    obscureText: String? = "*",
    onValueChanged: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    val keyboard = LocalSoftwareKeyboardController.current
    TextField(
        readOnly = disableKeypad,
        value = value,
        onValueChange = {
            if (it.length <= length) {
                if (it.all { c -> c in '0'..'9' }) {
                    onValueChanged(it)
                }
                if (it.length >= length) {
                    keyboard?.hide()
                }
            }
        },
        // Hide the text field
        modifier = Modifier
            .size(0.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused.value = it.isFocused
            },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(length) {
            OtpCell(
                modifier = cellModifier
                    .clickable {
                        focusRequester.requestFocus()
                        keyboard?.show()
                    },
                value = value.getOrNull(it),
                isCursorVisible = isFocused.value && value.length == it,
                obscureText,
            )
            if (it != length - 1)
                Spacer(modifier = Modifier.size(8.dp))
        }
    }
}


@DarkLightPreviews
@Composable
fun OTPPreview(modifier: Modifier = Modifier) {
    val enterPin = remember { mutableStateOf("") }
    PasswordComposeTheme {
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
        )
    }
}