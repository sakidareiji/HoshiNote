package com.example.hoshinote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import com.example.hoshinote.navigation.Routes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hoshinote.ui.theme.HoshiNoteTheme
import com.example.hoshinote.screen.ExplorationRecordScreen
import com.example.hoshinote.screen.GoalManagementScreen
import com.example.hoshinote.screen.GoalSettingScreen
import com.example.hoshinote.screen.SettingScreen
import com.example.hoshinote.screen.UserRegistrationScreen

data class BottomNavItem(
    val route: Routes,
    val icon: ImageVector,
    val label: String
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HoshiNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ){
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation(){
    val navController= rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    val bottomNavItems = listOf(
        BottomNavItem(Routes.GoalManagement, Icons.Default.Home, "ホーム"),
        BottomNavItem(Routes.ExplorationRecord, Icons.Default.Star, "探索記録"),
        BottomNavItem(Routes.Setting, Icons.Default.Settings,"設定" )
    )

    val showBottomBar = currentRoute != Routes.UserRegistration::class.qualifiedName

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color(0xd00B0E21)
                ) {
                    bottomNavItems.forEach { item ->
                        val isSelected = currentRoute == item.route::class.qualifiedName
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,
                                selectedTextColor = Color(0xFFB0BEC5),
                                indicatorColor = Color(0xFFB0BEC5),
                                unselectedIconColor = Color(0xFF757575),
                                unselectedTextColor = Color(0xFF757575)
                            ),
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = {
                                Text(text = item.label)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.UserRegistration,
            modifier = Modifier.padding(paddingValues),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ){
            composable<Routes.UserRegistration>{
                UserRegistrationScreen(navController)
            }
            composable<Routes.GoalSetting>{
                GoalSettingScreen(navController)
            }
            composable<Routes.GoalManagement>{
                GoalManagementScreen(navController)
            }
            composable<Routes.Setting>{
                SettingScreen()
            }
            composable<Routes.ExplorationRecord>{
                ExplorationRecordScreen(navController)
            }
        }
    }
}




