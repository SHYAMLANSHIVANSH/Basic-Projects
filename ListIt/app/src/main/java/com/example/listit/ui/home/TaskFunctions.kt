package com.example.listit.ui.home

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Updater
import androidx.compose.ui.platform.LocalContext
import com.example.listit.data.localDataStoreClass
import com.example.listit.data.localDataStoreClassReturn


fun SaveTask(
    title : String,
    task : String?,
    viewModel: HomeViewModel,
    context: Context
){
    viewModel.addTasks(context = context, localDataStoreClass(Title = title, Task = task))
}


fun DeleteTask(
    Id : Int,
    viewModel: HomeViewModel,
    context: Context
){
    viewModel.deleteTasks(context = context, id = Id)
}

fun UpdateTask(
    title : String,
    task : String?,
    Id : Int,
    viewModel: HomeViewModel,
    context: Context
){
    viewModel.updateTasks(context = context, localDataStoreClassReturn(Title = title, Task = task, Id = Id))
}