package com.example.listall

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listall.star.Star
import com.example.listall.home.Home
import com.example.listall.theme.AppTheme

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AllDestinations.HOME_ROUTE
){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(
            route = AllDestinations.HOME_ROUTE
        ){
            Home()
        }

        composable(
            route = AllDestinations.STAR_ROUTE
        ){
            Star()
        }

        composable(
            route = AllDestinations.THEME_ROUTE
        ){
            AppTheme()
        }
    }
}