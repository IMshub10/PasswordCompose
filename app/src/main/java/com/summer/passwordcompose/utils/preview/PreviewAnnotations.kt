package com.summer.passwordcompose.utils.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Dark Mode",
    group = "UI Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Preview(
    name = "Light Mode",
    group = "UI Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
annotation class DarkLightPreviews