package com.example.smartmobility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smartmobility.ui.theme.SmartMobilityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartMobility()
        }
    }
}

@Composable
fun BottomBar(navController: NavController, currentRoute: String?, modifier: Modifier = Modifier) {
    val bottomBarItemList = listOf<NavigationBarItem>(
        NavigationBarItem.Home,
        NavigationBarItem.Travel,
        NavigationBarItem.Guide,
        NavigationBarItem.Profile,
    )
    NavigationBar {
        bottomBarItemList.forEach { item ->
            NavigationBarItem(selected = item.route == currentRoute,
                onClick = {
                    navController.navigate(route = item.route) {
                        launchSingleTop = true
                        popUpTo(item.route)
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) })
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SmartMobility() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    SmartMobilityTheme {
        Scaffold(
            bottomBar = { BottomBar(navController, currentRoute) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavigationBarItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(NavigationBarItem.Home.route) {
                    HomeScreen()
                }
                composable(NavigationBarItem.Travel.route) {
                    TravelScreen()
                }
                composable(NavigationBarItem.Guide.route) {
                   GuideScreen()
                }
                composable(NavigationBarItem.Profile.route) {
                    ProfileScreen()
                }
            }
        }
    }
}