package com.summer.passwordcompose.presentation.common

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun CustomNavItem(
    onClick: () -> Unit,
    iconSelected: @Composable () -> Unit
) {
    IconButton(onClick = {
        onClick()
    },
        content = { iconSelected() }
    )
}