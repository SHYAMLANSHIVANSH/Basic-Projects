package com.example.listall.theme

import android.R.attr.height
import android.R.attr.theme
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listall.R
import com.example.listall.database.DataRepository
import com.example.listall.database.Theme
import com.example.listall.database.ThemeType

@Preview
@Composable
fun AppTheme(viewModel: ThemeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    Scaffold() {innerpadding->
        val context = LocalContext.current
        val Themes = AllThemes().Themes
        val selectedThemesId by viewModel.theme_id

        LaunchedEffect(Unit) {
            viewModel.LoadTheme(context)
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(160.dp),
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize(),
            verticalItemSpacing = 12.dp,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(Themes){themes->
                ThemeBox(
                    color = themes.Color,
                    Text = themes.Theme_Name,
                    onClick = {
                        viewModel.InsertTheme(themes.id, context)
                              },
                    selectedTheme = selectedThemesId,
                    id = themes.id,
                    contentColor = themes.contentColor
                )
            }
        }
    }
}


@Composable
fun ThemeBox(
    color: Color,
    Text : String,
    onClick : () -> Unit,
    selectedTheme : Long,
    id : Long,
    contentColor : Color
){
    val interactionSource = remember { MutableInteractionSource() }
    Box {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = color,
                contentColor = contentColor
            ),
            modifier = Modifier
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                    onClick = onClick
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(Text)
            }
        }
        if (selectedTheme == id) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF4CAF50)), // nice green
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.selected_theme),
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}