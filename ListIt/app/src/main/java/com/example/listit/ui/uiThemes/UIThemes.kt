package com.example.listit.ui.uiThemes

import android.R
import android.R.attr.onClick
import android.R.attr.theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listit.data.ThemeRepository
import com.example.listit.di.AppModule
import com.example.listit.utils.ThemeColor
import com.example.listit.utils.ThemeItem
import com.example.listit.utils.getTheCurrentTheme

@Preview
@Composable
fun UIThemes() {
    val context = LocalContext.current
    val viewModel = remember {
        AppModule.provideThemeViewModel(context)
    }

    val LoadThemes = getAllThemesList().LoadThemesList
    val theme = ThemeItem()

    val currentTheme = getTheCurrentTheme(context)

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(240.dp),
        verticalItemSpacing = 30.dp,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(LoadThemes){themes->
            theme.ThemeItemCardBox(
                color = themes,
                currentTheme = currentTheme,
                onClick = {
                    viewModel.saveTheme(themes)
                }
            )
        }
    }
}

