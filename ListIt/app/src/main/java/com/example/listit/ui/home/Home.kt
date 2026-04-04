package com.example.listit.ui.home

import android.app.Activity
import android.graphics.drawable.Icon
import android.util.Log
import android.util.Log.i
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import com.example.listit.data.localDataStoreClassReturn
import com.example.listit.navigation.HomeInnerAllDestinations
import com.example.listit.navigation.HomeInnerNavGraph
import com.example.listit.utils.ItemUI
import com.example.listit.utils.ThemeColor
import com.example.listit.utils.getTheCurrentTheme

@Composable
fun Home(viewModel: HomeViewModel){
    val navController = rememberNavController()

    HomeInnerNavGraph(
        navController = navController,
        viewModel = viewModel
    )
}
@Composable
fun HomeContent(viewModel : HomeViewModel, onAddClick : () -> Unit, onEditClick: (Int) -> Unit){
    val context = LocalContext.current
    val currentTheme = getTheCurrentTheme(context)
    val themeColor = ThemeColor(currentTheme)
    LaunchedEffect(true){
        viewModel.loadTasks(context)
    }
    val AllTasks = viewModel.task.collectAsState()
    val tasks = AllTasks.value
    val itemUI = ItemUI()
    val isLoading = viewModel.isLoading.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddClick()
                },
                containerColor = themeColor.containerColor,
                contentColor = themeColor.contentColor
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "This is to add the items in the list"
                )
            }
        }
    ) {innerPadding->
        Box(modifier = Modifier){
            when(isLoading.value){
                false -> {
                    if (tasks.isNotEmpty()) {
                        TaskNotEmptyList(
                            modifier = Modifier.fillMaxSize(),
                            tasks = tasks,
                            itemUI = itemUI,
                            currentTheme = currentTheme,
                            viewModel = viewModel,
                            onEditClick = {id->
                                onEditClick(id)
                                Log.d("HomeContent", "$id has been passed from here")
                            }
                        )
                    } else {
                        TaskEmpty(modifier = Modifier.fillMaxSize())
                    }
                }
                true ->{
                    LoadingScreen(themeColor)
                }
            }
        }
    }
}


@Composable
fun LoadingScreen(color : ThemeColor){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            color = color.containerColor
        )
    }
}
@Composable
fun TaskEmpty(modifier: Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("You don't have any task yet")
    }
}

@Composable
fun TaskNotEmptyList(modifier: Modifier, tasks : List<localDataStoreClassReturn>, itemUI : ItemUI, currentTheme: String, viewModel: HomeViewModel, onEditClick: (Int) -> Unit){
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.SpaceEvenly,
        columns = StaggeredGridCells.Fixed(1),
    ) {
        items(tasks) { task ->
            itemUI.ItemUIToShow(
                modifier = Modifier.sizeIn(minHeight = 40.dp),
                currentTheme = currentTheme,
                title = task.Title,
                task = task.Task,
                id = task.Id,
                viewModel = viewModel,
                onEditClick = {
                    onEditClick(task.Id)
                    Log.d("TaskNoEmptyList", "${task.Id} has been passed from here")
                }
            )
        }
    }
}