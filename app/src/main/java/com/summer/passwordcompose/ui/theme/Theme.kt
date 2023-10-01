package com.summer.passwordcompose.ui.theme

import android.app.Activity
import android.hardware.lights.Light
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = BlueLight,
    onPrimary = White,

    secondary = Black,
    onSecondary = White,

    tertiary = LightBlack,
    onTertiary = Grey,

    background = Black,
    onBackground = White,

    primaryContainer = Black,
    onPrimaryContainer = White,

    surface = LightBlack,

    onSurface = Black,

    error = RedDark
)

private val lightColorScheme = lightColorScheme(
    primary = BlueLight,
    onPrimary = Black,

    secondary = White,
    onSecondary = Black,

    tertiary = LightWhite,
    onTertiary = Grey,

    background = White,
    onBackground = Black,

    primaryContainer = White,
    onPrimaryContainer = Black,

    surface = LightWhite,
    onSurface = GreyLightest,

    error = RedDark,
)

@Composable
fun PasswordComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*/
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as Activity
            activity.window.statusBarColor = colorScheme.background.toArgb()
            activity.window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars =
                !darkTheme
            WindowCompat.getInsetsController(
                activity.window,
                view
            ).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}