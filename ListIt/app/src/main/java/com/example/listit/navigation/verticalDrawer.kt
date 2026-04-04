package com.example.listit.navigation

import android.R.attr.label
import android.R.attr.onClick
import android.content.res.Resources
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.listit.utils.ThemeColor
import com.example.listit.utils.getTheCurrentTheme

@Composable
fun VerticalDrawer(
    currentRoute: String?,
    navigateToHome: () -> Unit,
    navigateToTheme: () -> Unit,
    color: String
){
    val currentTheme = ThemeColor(color)
    NavigationRail(
        containerColor = currentTheme.containerColor,
        contentColor = currentTheme.contentColor
    ) {
        Spacer(Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute?.startsWith(AllDestinations.Home_Route) == true,
            onClick = {
                navigateToHome()
            },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = Color(0xFFc3d8fb),
                unselectedIconColor = currentTheme.contentColor
            ),
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Go to the home"
                )
            },
            label = {Text("Home", color = currentTheme.contentColor)}
        )
        Spacer(Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute?.startsWith(AllDestinations.Theme_Route) == true,
            onClick = {
                navigateToTheme()
            },
            colors = NavigationRailItemDefaults.colors(
                selectedIconColor = Color(0xFFc3d8fb),
                unselectedIconColor = currentTheme.contentColor
            ),
            icon = {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "Go to the home"
                )
            },
            label = {Text("Home", color = currentTheme.contentColor)}
        )
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun VerticalDrawerTest(){
    val context = LocalContext.current
    VerticalDrawer(
        AllDestinations.Home_Route,
        {},
        {},
        getTheCurrentTheme(context = context)
    )
}