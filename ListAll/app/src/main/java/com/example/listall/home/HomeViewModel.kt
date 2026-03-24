package com.example.listall.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.listall.database.DataRepository
import com.example.listall.database.DataType
import com.example.listall.database.TaskReturnDataType

class HomeViewModel() : ViewModel() {
    private val _all_data = DataRepository()

    fun getData(context: Context) : Array<TaskReturnDataType>{
        val get_list = _all_data.List(context)
        return get_list
    }
    fun insert_list(context: Context, DataType: DataType){
        val add_list = _all_data.Insert(DataType,context)
    }
    fun update_list(Id : Long,context: Context, Data : DataType){
        val update_list = _all_data.Update(Id,Data,context)
    }
    fun delete_list(context: Context, id : Long){
        val delete_list = _all_data.Delete(id, context)
    }

    fun insert_star(context: Context, values : DataType){
        val insert__star = _all_data.InsertStar(values, context)
    }
    fun all_star(context : Context) : Array<TaskReturnDataType>{
        val get_star = _all_data.ListStar(context)
        return get_star
    }
    fun delete_star(context: Context, id : Long){
        val delete_star = _all_data.DeleteStar(id, context)
    }
}