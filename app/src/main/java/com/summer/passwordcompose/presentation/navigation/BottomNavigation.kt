package com.summer.passwordcompose.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.summer.passwordcompose.presentation.common.CustomNavItem
import com.summer.passwordcompose.ui.theme.spacingSmall

@Composable
fun BottomNavigation(
    navController: NavController,
    bottomBarState: Boolean,
    snackbarHostState: SnackbarHostState,
) {
    val items = listOf(
        BottomNavItem.Vault,
        BottomNavItem.Generator,
        BottomNavItem.Profile
    )
    /*val userListViewModel: UserListViewModel = hiltViewModel()

    val toastMessage = userListViewModel.toastMessage.value
    LaunchedEffect(key1 = toastMessage) {
        if (toastMessage != "") {
            SnackbarController(this).showSnackbar(snackbarHostState, toastMessage, "Close")
        }
    }*/
    AnimatedVisibility(
        visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomAppBar(
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                CustomNavItem(
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    iconSelected = {
                        if (currentRoute == item.screen_route) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                            )
                        }
                    }
                )
                Spacer(Modifier.weight(1f, true))
            }
            /*if (currentRoute == BottomNavItem.UserList.screen_route) {
                var showAlertDialog by remember {
                    mutableStateOf(false)
                }
                *//*if (showAlertDialog) {
                    AlertDialogChat(
                        onDismiss = { showAlertDialog = !showAlertDialog },
                        onConfirm = {
                            showAlertDialog = !showAlertDialog
                            userListViewModel.createFriendshipRegisterToFirebase(it)
                        })
                }*//*
                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(end = spacingSmall),
                    onClick = {
                        showAlertDialog = !showAlertDialog
                    },
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp
                    )
                ) {
                    Text(text = "Add New Message")
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }*/
        }
    }
}