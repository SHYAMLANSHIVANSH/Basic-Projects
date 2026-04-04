package com.example.listit.ui.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listit.data.LocalDataRepository
import com.example.listit.data.ThemeRepository
import com.example.listit.data.localDataStoreClass
import com.example.listit.data.localDataStoreClassReturn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: LocalDataRepository
) : ViewModel() {

    private val _task = MutableStateFlow<List<localDataStoreClassReturn>>(emptyList())
    val task : StateFlow<List<localDataStoreClassReturn>> = _task

    val isLoading : MutableStateFlow<Boolean> = MutableStateFlow(true)

    fun loadTasks(context: Context){
        viewModelScope.launch(Dispatchers.Default) {
            try{
                isLoading.value = true
                val data = repository.TaskGetter(context)
                _task.value = data
            }catch (ex : Exception){
                Log.d("Loading Error", "This the is error in the loading")
            } finally {
                delay(600)
                isLoading.value = false
            }
        }
    }

    fun addTasks(context: Context, tasks : localDataStoreClass){
        viewModelScope.launch(Dispatchers.IO) {
            repository.TaskAdder(context, tasks)
            loadTasks(context)
        }
    }

    fun deleteTasks(context: Context, id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.TaskDeleter(context, id)
            loadTasks(context)
        }
    }

    fun updateTasks(context: Context, tasks : localDataStoreClassReturn){
        viewModelScope.launch(Dispatchers.IO) {
            repository.TaskUpdater(context, tasks)
            loadTasks(context)
        }
    }
}