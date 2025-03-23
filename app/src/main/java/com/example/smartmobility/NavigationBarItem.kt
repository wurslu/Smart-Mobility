package com.example.smartmobility

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationBarItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Home : NavigationBarItem(route = "home", icon = Icons.Filled.Home, title = "主页")
    object Travel :
        NavigationBarItem(route = "travel", icon = Icons.Filled.DirectionsBus, title = "出行")

    object Guide :
        NavigationBarItem(route = "guide", icon = Icons.Filled.TravelExplore, title = "导览")

    object Profile :
        NavigationBarItem(route = "profile", icon = Icons.Filled.Person, title = "我的")
}