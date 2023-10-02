package com.summer.passwordcompose.presentation.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AppSnackBar(
    snackbarData: SnackbarData,
    actionOnNewLine: Boolean = false,
) {
    val actionLabel = snackbarData.visuals.actionLabel
    val actionComposable: (@Composable () -> Unit)? = if (actionLabel != null) {
        @Composable {
            TextButton(
                onClick = { snackbarData.performAction() },
                content = { Text(actionLabel) }
            )
        }
    } else {
        null
    }
    Snackbar(
        content = { Text(snackbarData.visuals.message) },
        action = actionComposable,
        actionOnNewLine = actionOnNewLine,
    )
}