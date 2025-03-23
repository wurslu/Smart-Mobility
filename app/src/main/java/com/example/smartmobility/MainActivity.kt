package com.example.smartmobility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(text = "杭州市西湖区", modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
            Icon(
                imageVector = Icons.Filled.QrCode,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(query = "",
                    onQueryChange = { },
                    onSearch = { },
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = { Text("搜索目的地、公交路线") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "搜索",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "语音输入",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    },
                    colors = SearchBarDefaults.inputFieldColors(),
                    interactionSource = remember { MutableInteractionSource() })
            },
            expanded = false,
            onExpandedChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            content = {})
        HorizontalDivider(
            modifier = Modifier.padding(top = 8.dp),
            color = DividerDefaults.color.copy(alpha = 0.2f)
        )
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
    NavigationBar(
        modifier
    ) {
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
            topBar = { TopBar() },
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