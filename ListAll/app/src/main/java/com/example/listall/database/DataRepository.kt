package com.example.listall.database

import android.content.Context

class DataRepository() {
    // For the star data
    fun InsertStar(Values : DataType, context: Context){
        val db = Database(context)
        db.InsertStar(Values)
    }
    fun DeleteStar(id : Long, context: Context){
        val db = Database(context)
        db.DeleteStar(id)
    }
    fun ListStar(context : Context): Array<TaskReturnDataType>{
        val db = Database(context)
        val list = db.GetStar()
        return list
    }
    // For the task data
    fun Insert(Values : DataType, context: Context){
        val db = Database(context)
        db.Insert(Values)
    }
    fun Delete(id : Long, context: Context){
        val db = Database(context)
        db.Delete(id)
    }
    fun Update(Id : Long,Values : DataType, context: Context){
        val db = Database(context)
        db.Update(Id,Values)
    }
    fun List(context : Context): Array<TaskReturnDataType>{
        val db = Database(context)
        val list = db.Get()
        return list
    }
    // For the theme data

    fun InsertTheme(ThemeType : ThemeType,context: Context){
        val db = Database(context)
        db.InsertTheme(ThemeType)
    }
    fun ListTheme(context: Context): ThemeType?{
        val db = Database(context)
        val SelectedTheme = db.getTheme()
        return SelectedTheme
    }
}