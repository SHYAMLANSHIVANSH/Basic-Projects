package com.example.listit.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listit.data.LocalDataRepository
import com.example.listit.data.ThemeRepository
import com.example.listit.data.localDataStoreClass
import com.example.listit.data.localDataStoreClassReturn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: LocalDataRepository
) : ViewModel() {

    private val _task = MutableStateFlow<List<localDataStoreClassReturn>>(emptyList())
    val task : StateFlow<List<localDataStoreClassReturn>> = _task

    fun loadTasks(context: Context){
        viewModelScope.launch {
            val data = repository.TaskGetter(context)
            _task.value = data
        }
    }

    fun addTasks(context: Context, tasks : localDataStoreClass){
        viewModelScope.launch {
            repository.TaskAdder(context, tasks)
            loadTasks(context)
        }
    }

    fun deleteTasks(context: Context, id : Int){
        viewModelScope.launch {
            repository.TaskDeleter(context, id)
            loadTasks(context)
        }
    }

    fun updateTasks(context: Context, tasks : localDataStoreClassReturn){
        viewModelScope.launch {
            repository.TaskUpdater(context, tasks)
            loadTasks(context)
        }
    }
}