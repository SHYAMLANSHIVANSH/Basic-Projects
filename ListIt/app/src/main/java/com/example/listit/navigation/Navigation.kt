package com.example.listit.navigation

import androidx.navigation.NavHostController

object AllDestinations {
    const val Home_Route = "Home"
    const val Theme_Route = "Theme"
}

class Navigation(navController: NavHostController){

    val NavigateToHome : () -> Unit = {
        navController.navigate(AllDestinations.Home_Route)
    }
    val NavigateToTheme : () -> Unit = {
        navController.navigate(AllDestinations.Theme_Route)
    }
}