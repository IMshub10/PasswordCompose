package com.summer.passwordcompose.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.summer.passwordcompose.presentation.navigation.BottomNavItem
import com.summer.passwordcompose.presentation.screens.PassGeneratorScreen
import com.summer.passwordcompose.presentation.screens.ProfileScreen
import com.summer.passwordcompose.presentation.screens.SetPinScreen
import com.summer.passwordcompose.presentation.screens.SignUpScreen
import com.summer.passwordcompose.presentation.screens.VaultScreen

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController
) {
    com.google.accompanist.navigation.animation.AnimatedNavHost(
        navController,
        startDestination = BottomNavItem.SignUp.fullRoute
    ) {
        //SIGN IN SCREEN
        composable(
            BottomNavItem.SignUp.fullRoute,
            arguments = listOf(),
            enterTransition = {
                null
            }
        ) {
            SignUpScreen(navController = navController, keyboardController = keyboardController)
        }
        composable(
            BottomNavItem.SetPin.fullRoute,
            arguments = listOf(),
            enterTransition = {
                null
            }
        ) {
            SetPinScreen(keyboardController = keyboardController)
        }
        composable(
            BottomNavItem.Vault.fullRoute,
        ) {
            VaultScreen(navController = navController, keyboardController = keyboardController)
        }
        composable(
            BottomNavItem.Generator.fullRoute,
        ) {
            PassGeneratorScreen(
                navController = navController,
                keyboardController = keyboardController
            )
        }
        composable(
            BottomNavItem.Profile.fullRoute,
        ) {
            ProfileScreen(navController = navController, keyboardController = keyboardController)
        }
    }
}