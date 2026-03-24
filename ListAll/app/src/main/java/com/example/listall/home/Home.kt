package com.example.listall.home

import android.R.attr.label
import android.R.attr.text
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.listall.database.DataRepository
import com.example.listall.database.DataType
import com.example.listall.database.TaskReturnDataType
import com.example.listall.database.Theme
import com.example.listall.theme.AllThemes
import kotlin.collections.mutableListOf

@Composable
fun Home(viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    var AddOrNotBollean by remember { mutableStateOf(false) }
    val ThemeData = DataRepository()
    val context = LocalContext.current
    val ThemeBackground = ThemeData.ListTheme(context)
    val ThemeId = ThemeBackground?.id ?: 1
    val ThemeSearch = AllThemes().Themes.get(ThemeId.toInt())
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {AddOrNotBollean = true}, containerColor = ThemeSearch.Color, contentColor = ThemeSearch.contentColor) {
                val ThemeData = DataRepository()
                val context = LocalContext.current
                val ThemeBackground = ThemeData.ListTheme(context)
                val ThemeId = ThemeBackground?.id ?: 1
                val ThemeSearch = AllThemes().Themes.get(ThemeId.toInt())
                Icon(
                    Icons.Default.AddCircle,
                    contentDescription = "This is to add items",
                    tint = ThemeSearch.contentColor
                )
            }
        }
    ){innerpadding->
        val all_list = viewModel.getData(context)
        Box(){
            var Value by remember { mutableStateOf("") }
            var showErrorTextIfEmpty by remember { mutableStateOf(false) }
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(1),
                verticalItemSpacing = 12.dp,
                horizontalArrangement = Arrangement.Center
            ){
                if (AddOrNotBollean) {
                    item{
                            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(6.dp)),
                                    value = Value,
                                    onValueChange = { Value = it },
                                    label = { Text("Add Tasks") },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = ThemeSearch.Color,
                                        unfocusedContainerColor = ThemeSearch.Color,
                                        focusedTextColor = ThemeSearch.contentColor,
                                        unfocusedTextColor = ThemeSearch.contentColor,
                                        unfocusedLabelColor = ThemeSearch.contentColor,
                                        focusedLabelColor = ThemeSearch.contentColor
                                    )
                                )
                                if (showErrorTextIfEmpty == true) {
                                    Text("Please enter a task", color = Color.Red)
                                }
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                                    Button(
                                        colors = ButtonDefaults.buttonColors(
                                        contentColor = ThemeSearch.contentColor,
                                        containerColor = ThemeSearch.Color
                                    ),
                                        onClick = {
                                            if (Value.isEmpty()) {
                                                showErrorTextIfEmpty = true
                                            }

                                            else{
                                                showErrorTextIfEmpty = false
                                                AddOrNotBollean = false
                                                viewModel.insert_list(context, DataType(Value))
                                            }
                                        }
                                    ) {
                                        Text("Save")
                                    }
                                    Button(
                                        colors = ButtonDefaults.buttonColors(
                                            contentColor = ThemeSearch.contentColor,
                                            containerColor = ThemeSearch.Color
                                        ),
                                        onClick = {
                                            AddOrNotBollean = false
                                        }
                                    ) {
                                        Text("Delete")
                                    }
                                }
                            }
                        }
                    }
                items(all_list){list->
                    ItemsList(Modifier.fillMaxWidth(),ThemeSearch,list)
                }
                }
            }
        }
    }
@Composable
fun ItemsList(modifier: Modifier,ThemeSearch : Theme, Text : TaskReturnDataType, viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), context : Context = LocalContext.current){
    var isVisible by remember { mutableStateOf(true) }
    var isEditModeOn by remember { mutableStateOf(false) }
    var Value by remember { mutableStateOf(Text.Task) }
    var showErrorTextIfEmpty by remember { mutableStateOf(false) }
    if(isEditModeOn){
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp)),
                value = Value,
                onValueChange = { Value = it },
                label = { Text("Add Tasks") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = ThemeSearch.Color,
                    unfocusedContainerColor = ThemeSearch.Color,
                    focusedTextColor = ThemeSearch.contentColor,
                    unfocusedTextColor = ThemeSearch.contentColor,
                    unfocusedLabelColor = ThemeSearch.contentColor,
                    focusedLabelColor = ThemeSearch.contentColor
                )
            )
            if (showErrorTextIfEmpty == true) {
                Text("Please enter a task", color = Color.Red)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        contentColor = ThemeSearch.contentColor,
                        containerColor = ThemeSearch.Color
                    ),
                    onClick = {
                        if (Value.isEmpty()) {
                            showErrorTextIfEmpty = true
                        }

                        else{
                            showErrorTextIfEmpty = false
                            isEditModeOn = false
                            viewModel.update_list(Text.Id, context, DataType(Value))
                        }
                    }
                ) {
                    Text("Save")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        contentColor = ThemeSearch.contentColor,
                        containerColor = ThemeSearch.Color
                    ),
                    onClick = {
                        isEditModeOn = false
                    }
                ) {
                    Text("Discard")
                }
            }
        }
    }
    else{
        AnimatedVisibility(
            visible = isVisible,
            exit = slideOutHorizontally(
                targetOffsetX = { -it }, // slide out to the left
                animationSpec = tween(durationMillis = 500)
            ) + fadeOut(animationSpec = tween(500))
        ) {
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
                            viewModel.insert_star(context, DataType(Text.Task))
                            viewModel.delete_list(context, Text.Id)
                            isVisible = false
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = ThemeSearch.Color,
                            contentColor = ThemeSearch.contentColor
                        )
                    ) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "This is to star a list item"
                        )
                    }
                    IconButton(
                        onClick = {
                            isEditModeOn = true
                            isVisible = true
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = ThemeSearch.Color,
                            contentColor = ThemeSearch.contentColor
                        )
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "This is to edit a list item"
                        )
                    }
                    IconButton(
                        onClick = {
                            viewModel.delete_list(context, Text.Id)
                            isVisible = false
                        },
                        interactionSource = remember { MutableInteractionSource() },
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
}