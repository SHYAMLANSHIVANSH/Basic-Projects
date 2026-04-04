package com.example.listit.navigation

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listit.utils.ThemeColor
import com.example.listit.utils.getTheCurrentTheme

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
fun CustomDrawer(
    currentRoute: String?,
    navigateToHome: () -> Unit,
    navigateToTheme: () -> Unit,
    color: String
){
    val currentTheme = ThemeColor(color)
    NavigationRail(containerColor = currentTheme.containerColor, contentColor = currentTheme.contentColor, modifier = Modifier.width(290.dp)) {
        Spacer(Modifier.weight(1f))
        CustomDrawerItem(
            selected = currentRoute?.startsWith(AllDestinations.Home_Route) == true,
            onClick = {
                navigateToHome()
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "GO To The Home",
                    tint = currentTheme.contentColor
                )
            },
            label = {Text("Home", color = currentTheme.contentColor)}
        )

        Spacer(Modifier.weight(1f))

        CustomDrawerItem(
            selected = currentRoute?.startsWith(AllDestinations.Theme_Route) == true,
            onClick = {
                navigateToTheme()
            },
            icon = {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "Go to the Themes",
                    tint = currentTheme.contentColor
                )
            },
            label = {Text("Theme", color = currentTheme.contentColor)}
        )
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun CustomDrawerTest(){
    val context = LocalContext.current
    CustomDrawer(
        AllDestinations.Home_Route,
        {},
        {},
        getTheCurrentTheme(context = context)
    )
}
