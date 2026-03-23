package com.example.listall

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DrawerVertical(
    modifier: Modifier,
    currentRoute: String?,
    navigateToHome: () -> Unit,
    navigateToStar: () -> Unit,
    navigateToTheme: () -> Unit
){
    NavigationRail() {
        Spacer(Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute == AllDestinations.HOME_ROUTE,
            onClick = {
                navigateToHome()
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.home_icon)
                )
            },
            label = {Text("Home")}
        )

        Spacer(Modifier.weight(1f))

        NavigationRailItem(
            selected = currentRoute == AllDestinations.STAR_ROUTE,
            onClick = {
                navigateToStar()
            },
            icon = {
                Icon(
                    Icons.Default.Star,
                    contentDescription = stringResource(R.string.starTask_icon)
                )
            },
            label = {Text("ToDo")}
        )
        Spacer(Modifier.weight(1f))

        NavigationRailItem(
            selected = currentRoute == AllDestinations.THEME_ROUTE,
            onClick = {
                navigateToTheme()
            },
            icon = {
                Icon(
                    Icons.Default.Create,
                    contentDescription = stringResource(R.string.theme_icon)
                )
            },
            label = {Text("Theme")}
        )
        Spacer(Modifier.weight(1f))
    }
}