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
import com.summer.passwordcompose.presentation.screens.SignUpScreen

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
            SignUpScreen()
        }
        composable(
            BottomNavItem.Vault.fullRoute,
        ) {
            SignUpScreen()
        }
        composable(
            BottomNavItem.Generator.fullRoute,
        ) {
            SignUpScreen()
        }
        composable(
            BottomNavItem.Profile.fullRoute,
        ) {
            SignUpScreen()
        }
    }
}