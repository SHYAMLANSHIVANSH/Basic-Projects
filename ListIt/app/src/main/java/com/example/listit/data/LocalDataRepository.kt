package com.example.listit.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class LocalDataRepository {
    suspend fun TaskAdder(context: Context, localDataStoreClass: localDataStoreClass){
        val localData = localData(context)
        withContext(Dispatchers.IO){
            localData.Add_Task(localDataStoreClass)
        }
    }
    suspend fun TaskDeleter(context: Context, id : Int){
        val localData = localData(context)
        withContext(Dispatchers.IO){
            localData.Delete_Task(id)
        }
    }
    suspend fun TaskUpdater(context: Context, localDataStoreClassReturn: localDataStoreClassReturn){
        val localData = localData(context)
        withContext(Dispatchers.IO){
            localData.Update_Task(localDataStoreClassReturn)
        }
    }
    suspend fun TaskGetter(context: Context) : List<localDataStoreClassReturn>{
        val localData = localData(context)
        val data : List<localDataStoreClassReturn>
        withContext(Dispatchers.Default){
            data = localData.Get_Task()
        }
        return data
    }
}