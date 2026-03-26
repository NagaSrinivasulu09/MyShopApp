package com.example.my_shop.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf("home", "favorites", "profile")

    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {

        items.forEach { route ->

            NavigationBarItem(
                selected = currentRoute == route,
                onClick = {
                    navController.navigate(route) {
                        popUpTo("home")
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = when (route) {
                            "home" -> Icons.Default.Home
                            "favorites" -> Icons.Default.Favorite
                            "profile" -> Icons.Default.Person
                            else -> Icons.Default.Home
                        },
                        contentDescription = route
                    )
                },
                label = {
                    Text(route)
                }
            )
        }
    }
}