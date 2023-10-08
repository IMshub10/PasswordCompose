package com.summer.passwordcompose.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.summer.passwordcompose.presentation.common.AppSnackBar
import com.summer.passwordcompose.presentation.navigation.BottomNavItem
import com.summer.passwordcompose.presentation.navigation.BottomNavigation
import com.summer.passwordcompose.ui.theme.PasswordComposeTheme
import com.summer.passwordcompose.utils.AppKeyboardFocusManager
import com.summer.passwordcompose.utils.preview.DarkLightPreviews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppKeyboardFocusManager()
            PasswordComposeTheme {
                MainScreenView()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun MainScreenView() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                AppSnackBar(
                    snackbarData = data
                )
            }
        },
        bottomBar = {
            bottomBarState.value =
                currentRoute !in arrayOf(
                    BottomNavItem.SignUp.fullRoute,
                    BottomNavItem.SetPin.fullRoute
                )
            BottomNavigation(
                navController = navController,
                bottomBarState = bottomBarState.value,
                snackbarHostState
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            HorizontalPager(
//                count = items.
////                state = pagerState
//            ) {
            NavGraph(
                navController = navController,
                snackbarHostState = snackbarHostState,
                keyboardController = keyboardController!!
            )
//            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@DarkLightPreviews
@Composable
fun GreetingPreview() {
    PasswordComposeTheme {
        Greeting("Android")
    }
}