package com.summer.passwordcompose.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.summer.passwordcompose.ui.theme.RedLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLinedEditText(
    modifier: Modifier = Modifier,
    startIcon: ImageVector,
    hint: String = "",
    description: String = hint,
    required: Boolean = false,
    onChange: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        label = {
            Text(text = buildAnnotatedString {
                append(hint)
                if (required) {
                    withStyle(style = SpanStyle(RedLight)) {
                        append("*")
                    }
                }
            }, color = MaterialTheme.colorScheme.onSecondary)
        },
        onValueChange = {
            text = it
            onChange(it)
        },

        leadingIcon = {
            Icon(
                imageVector = startIcon,
                contentDescription = description
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onTertiary,
            textColor = MaterialTheme.colorScheme.onSecondary,
        ),
    )
}