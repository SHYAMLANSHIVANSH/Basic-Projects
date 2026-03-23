package com.example.listall

import androidx.navigation.NavHostController

object AllDestinations{
    const val HOME_ROUTE = "Home"
    const val STAR_ROUTE = "Star"
    const val THEME_ROUTE = "Theme"
}

class Navigation(navController : NavHostController) {
    val NavigateToHome: () -> Unit = {
        navController.navigate(AllDestinations.HOME_ROUTE)
    }

    val NavigateToStar: () -> Unit = {
        navController.navigate(AllDestinations.STAR_ROUTE)
    }

    val NavigateToTheme: () -> Unit = {
        navController.navigate(AllDestinations.THEME_ROUTE)
    }
}