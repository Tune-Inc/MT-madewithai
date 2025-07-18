package com.tuneinc.multitune.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tuneinc.multitune.ui.components.BottomBar
import com.tuneinc.multitune.ui.components.NowPlayingBar
import com.tuneinc.multitune.ui.screens.ExploreScreen
import com.tuneinc.multitune.ui.screens.HomeScreen
import com.tuneinc.multitune.ui.screens.LibraryScreen
import com.tuneinc.multitune.ui.screens.PlayerScreen
import com.tuneinc.multitune.ui.screens.SearchScreen
import com.tuneinc.multitune.ui.screens.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiTuneApp(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    Scaffold(
        bottomBar = { 
            if (currentRoute != "player") {
                BottomBar(navController = navController)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { 
                HomeScreen(navController = navController)
            }
            composable("explore") { 
                ExploreScreen(navController = navController)
            }
            composable("library") { 
                LibraryScreen(navController = navController)
            }
            composable("search") { 
                SearchScreen(navController = navController)
            }
            composable("settings") { 
                SettingsScreen(navController = navController)
            }
            composable("player") { 
                PlayerScreen(navController = navController)
            }
        }
        
        if (currentRoute != "player") {
            NowPlayingBar(navController = navController)
        }
    }
}