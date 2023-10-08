package com.summer.passwordcompose.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screen_route: String,
    var arguments: String
) {

    object SignUp : BottomNavItem(
        "SignUp",
        Icons.Filled.Person,
        "signup",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }

    object SetPin : BottomNavItem(
        "SetPin",
        Icons.Filled.Person,
        "set_pin",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }

    object Vault : BottomNavItem(
        "Vault",
        Icons.Filled.Person,
        "vault",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }


    object Generator : BottomNavItem(
        "Generator",
        Icons.Filled.Person,
        "generator",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }


    object Profile : BottomNavItem(
        "Profile",
        Icons.Filled.Person,
        "profile",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }


}