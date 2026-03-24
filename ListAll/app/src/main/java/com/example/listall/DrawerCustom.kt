package com.example.listall

import android.R.attr.label
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.listall.database.DataRepository
import com.example.listall.theme.AllThemes

@Composable
fun CustomDrawerItem(
    selected : Boolean,
    onClick : () -> Unit,
    icon : @Composable () -> Unit,
    label : @Composable () -> Unit
){
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier
          .clickable(
              interactionSource = interactionSource,
              indication = LocalIndication.current,
              onClick = onClick
          )
          .padding(start = 2.dp)
          .width(260.dp)
          .height(60.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(if (selected) Color(0xFFc3d8fb) else Color.Transparent)
){
        icon()
        label()
    }
}

@Composable
fun DrawerCustom(
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
    NavigationRail(containerColor = ThemeSearch.Color, contentColor = ThemeSearch.contentColor) {
        Spacer(Modifier.weight(1f))
        CustomDrawerItem(
            selected = currentRoute == AllDestinations.HOME_ROUTE,
            onClick = {
                navigateToHome()
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = stringResource(R.string.home_icon),
                    tint = ThemeSearch.contentColor
                )
            },
            label = {Text("Home", color = ThemeSearch.contentColor)}
        )

        Spacer(Modifier.weight(1f))

        CustomDrawerItem(
            selected = currentRoute == AllDestinations.STAR_ROUTE,
            onClick = {
                navigateToStar()
            },
            icon = {
                Icon(
                    Icons.Default.Star,
                    contentDescription = stringResource(R.string.starTask_icon),
                    tint = ThemeSearch.contentColor
                )
            },
            label = {Text("ToDo", color = ThemeSearch.contentColor)}
        )
        Spacer(Modifier.weight(1f))

        CustomDrawerItem(
            selected = currentRoute == AllDestinations.THEME_ROUTE,
            onClick = {
                navigateToTheme()
            },
            icon = {
                Icon(
                    Icons.Default.Create,
                    contentDescription = stringResource(R.string.theme_icon),
                    tint = ThemeSearch.contentColor
                )
            },
            label = {Text("Theme", color = ThemeSearch.contentColor)}
        )
        Spacer(Modifier.weight(1f))
    }
}