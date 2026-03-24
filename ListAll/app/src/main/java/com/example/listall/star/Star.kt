package com.example.listall.star

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.listall.database.DataRepository
import com.example.listall.database.DataType
import com.example.listall.database.TaskReturnDataType
import com.example.listall.database.Theme
import com.example.listall.home.HomeViewModel
import com.example.listall.theme.AllThemes

@Composable
fun Star(viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    val ThemeData = DataRepository()
    val context = LocalContext.current
    val ThemeBackground = ThemeData.ListTheme(context)
    val ThemeId = ThemeBackground?.id ?: 1
    val ThemeSearch = AllThemes().Themes.get(ThemeId.toInt())
    val StarList = viewModel.all_star(context)
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(1),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.Center
    ) {
        items(StarList){lists->
            StarItem(Modifier.fillMaxWidth(), ThemeSearch, lists)
        }
    }
}

@Composable
fun StarItem(modifier: Modifier,ThemeSearch : Theme, Text : TaskReturnDataType, viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), context : Context = LocalContext.current){
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        exit = slideOutHorizontally(
            targetOffsetX = { -it }, // slide out to the left
            animationSpec = tween(durationMillis = 500)
        ) + fadeOut(animationSpec = tween(500))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(11.dp))
                .background(ThemeSearch.Color)
        ) {
            Text(
                modifier = Modifier
                    .offset(x = 9.dp)
                    .weight(2.4f),
                text = Text.Task,
                color = ThemeSearch.contentColor
            )
            Spacer(Modifier.weight(1f))
            Row(modifier = Modifier) {
                IconButton(
                    onClick = {
                        viewModel.insert_list(context, DataType(Text.Task))
                        viewModel.delete_star(context, Text.Id)
                        isVisible = false
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = ThemeSearch.Color,
                        contentColor = ThemeSearch.contentColor
                    )
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "This is to star a list item"
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.delete_star(context, Text.Id)
                        isVisible = false
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = ThemeSearch.Color,
                        contentColor = ThemeSearch.contentColor
                    )
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "This is to delete a list item"
                    )
                }
            }
        }
    }
}