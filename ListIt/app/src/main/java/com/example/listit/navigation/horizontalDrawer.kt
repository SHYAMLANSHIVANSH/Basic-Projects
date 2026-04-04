package com.example.listit.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.listit.utils.ThemeColor
import com.example.listit.utils.getTheCurrentTheme

@Composable
fun HorizontalDrawer(
    currentRoute: String?,
    navigateToHome: () -> Unit,
    navigateToTheme: () -> Unit,
    color: String
){
    val currentTheme = ThemeColor(color)
    NavigationBar(containerColor = currentTheme.containerColor, contentColor = currentTheme.contentColor) {
        NavigationBarItem(
            selected = currentRoute?.startsWith(AllDestinations.Home_Route) == true,
            onClick = {
                navigateToHome()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFc3d8fb),
                unselectedIconColor = currentTheme.contentColor
            ),
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "GO to the home"
                )
            },
            label = {Text("Home", color = currentTheme.contentColor)}
        )
        NavigationBarItem(
            selected = currentRoute?.startsWith(AllDestinations.Theme_Route) == true,
            onClick = {
                navigateToTheme()
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFc3d8fb),
                unselectedIconColor = currentTheme.contentColor
            ),
            icon = {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "Go to the themes"
                )
            },
            label = {Text("Theme", color = currentTheme.contentColor)}
        )
    }
}

@Preview
@Composable
fun HorizontalDrawerTest(){
    val context = LocalContext.current
    HorizontalDrawer(
        AllDestinations.Home_Route,
        {},
        {},
        getTheCurrentTheme(context = context)
    )
}
