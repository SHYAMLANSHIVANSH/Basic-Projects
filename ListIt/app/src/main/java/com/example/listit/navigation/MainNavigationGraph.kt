package com.example.listit.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.window.core.layout.WindowSizeClass
import com.example.listit.ui.home.Home
import com.example.listit.ui.home.HomeViewModel
import com.example.listit.ui.uiThemes.UIThemes
import com.example.listit.utils.ThemeColor

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    startDestination: String,
    viewModel: HomeViewModel,
    screenSize: androidx.compose.material3.windowsizeclass.WindowSizeClass,
    color: String
){
    val currentRoute = currentRoute(navController)?: AllDestinations.Home_Route
    when(screenSize.widthSizeClass){
        WindowWidthSizeClass.Compact ->{
            Compact(
                navController,
                color,
                currentRoute,
                startDestination,
                viewModel
            )
        }
        WindowWidthSizeClass.Medium ->{
            Medium(
                navController,
                color,
                currentRoute,
                startDestination,
                viewModel
            )
        }
        WindowWidthSizeClass.Expanded ->{
            Expanded(
                navController,
                color,
                currentRoute,
                startDestination,
                viewModel
            )
        }
        else -> {
            Compact(
                navController,
                color,
                currentRoute,
                startDestination,
                viewModel
            )
        }
    }
}

@Composable
fun NavHoster(navController: NavHostController, currentRoute: String, startDestination : String, viewModel: HomeViewModel){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(
            route = AllDestinations.Home_Route
        ){
            Home(viewModel)
        }
        composable(
            route = AllDestinations.Theme_Route
        ){
            UIThemes()
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    return navBackStackEntry.value?.destination?.route
}
@Composable
fun Compact(navController: NavHostController, color: String, currentRoute: String, startDestination: String, viewModel: HomeViewModel){
    val navigator = Navigation(navController)
    Scaffold(
        bottomBar = {
            BottomAppBar(){
                HorizontalDrawer(
                    currentRoute,
                    navigator.NavigateToHome,
                    navigator.NavigateToTheme,
                    color
                )
            }
        }
    ) { innerPadding->
        Box(modifier = Modifier.padding(innerPadding)){
            NavHoster(
                navController,
                currentRoute,
                startDestination,
                viewModel
            )
        }
    }
}

@Composable
fun Medium(navController: NavHostController, color: String, currentRoute: String, startDestination: String, viewModel: HomeViewModel){
    val navigator = Navigation(navController)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding->
        Box(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier.fillMaxSize()){
                VerticalDrawer(
                    currentRoute,
                    navigator.NavigateToHome,
                    navigator.NavigateToTheme,
                    color
                )
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(innerPadding)){
                    NavHoster(
                        navController,
                        currentRoute,
                        startDestination,
                        viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Expanded(navController: NavHostController, color: String, currentRoute: String, startDestination: String, viewModel: HomeViewModel){
    val navigator = Navigation(navController)
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding->
        Box(modifier = Modifier.fillMaxSize()){
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(6.dp)){
                CustomDrawer(
                    currentRoute,
                    navigator.NavigateToHome,
                    navigator.NavigateToTheme,
                    color
                )
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(innerPadding)){
                    NavHoster(
                        navController,
                        currentRoute,
                        startDestination,
                        viewModel
                    )
                }
            }
        }
    }
}