package com.example.listit.navigation

import androidx.navigation.NavHostController

object AllDestinations {
    const val Home_Route = "Home"
    const val Theme_Route = "Theme"
    const val Edit_Route = "Edit"
}

class Navigation(navController: NavHostController){

    val NavigateToHome : () -> Unit = {
        navController.navigate(AllDestinations.Home_Route)
    }
    val NavigateToTheme : () -> Unit = {
        navController.navigate(AllDestinations.Theme_Route)
    }
    val NavigateToEdit : () -> Unit = {
        navController.navigate(AllDestinations.Edit_Route)
    }
}