package com.example.listit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.listit.di.AppModule
import com.example.listit.ui.home.Home
import com.example.listit.ui.uiThemes.UIThemes

@Composable
fun MainNavigationGraph(
    navController : NavHostController,
    startDestination: String = AllDestinations.Home_Route
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(
            route = AllDestinations.Home_Route
        ){
            Home(AppModule.provideLocalDataViewModel(LocalContext.current))
        }
        composable(
            route = AllDestinations.Theme_Route
        ){
            UIThemes()
        }
    }
}