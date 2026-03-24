package com.example.listall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.listall.database.DataRepository
import com.example.listall.theme.AllThemes

@Composable
    fun DrawerHorizontal(
        modifier: Modifier,
        currentRoute: String?,
        navigateToHome: () -> Unit,
        navigateToStar: () -> Unit,
        navigateToTheme: () -> Unit
    ){
        val ThemeData = DataRepository()
        val context = LocalContext.current
        val ThemeBackground = ThemeData.ListTheme(context)
        val ThemeId = ThemeBackground?.id ?: 1
        val ThemeSearch = AllThemes().Themes.get(ThemeId.toInt())
        NavigationBar(containerColor = ThemeSearch.Color, contentColor = ThemeSearch.contentColor) {
            NavigationBarItem(
                selected = currentRoute == AllDestinations.HOME_ROUTE,
                onClick = {
                    navigateToHome()
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFc3d8fb),
                    unselectedIconColor = ThemeSearch.contentColor
                ),
                icon = {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = stringResource(R.string.home_icon)
                    )
                },
                label = {Text("Home", color = ThemeSearch.contentColor)}
            )

            NavigationBarItem(
                selected = currentRoute == AllDestinations.STAR_ROUTE,
                onClick = {
                    navigateToStar()
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFc3d8fb),
                    unselectedIconColor = ThemeSearch.contentColor
                ),
                icon = {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = stringResource(R.string.starTask_icon)
                    )
                },
                label = {Text("ToDo", color = ThemeSearch.contentColor)}
            )

            NavigationBarItem(
                selected = currentRoute == AllDestinations.THEME_ROUTE,
                onClick = {
                  navigateToTheme()
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFc3d8fb),
                    unselectedIconColor = ThemeSearch.contentColor
                ),
                icon = {
                    Icon(
                        Icons.Default.Create,
                        contentDescription = stringResource(R.string.theme_icon)
                    )
                },
                label = {Text("Theme", color = ThemeSearch.contentColor)}
            )
        }
    }