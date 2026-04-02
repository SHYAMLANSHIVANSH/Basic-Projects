package com.example.listit.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val User_Data = localDataConstants

class localData(context: Context) : SQLiteOpenHelper(context ,User_Data.Database_Name,null, User_Data.version  ){
    override fun onCreate(db: SQLiteDatabase?) {
        val Task_Table = """
            CREATE TABLE ${User_Data.Table_Name}(
            ${User_Data.key} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${User_Data.Title} TEXT NOT NULL,
            ${User_Data.Task} TEXT
            )
        """.trimIndent()
        db?.execSQL(Task_Table)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS ${User_Data.Table_Name}")
        onCreate(db)
    }

    fun Add_Task(localDataStoreClass: localDataStoreClass){
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(User_Data.Title, localDataStoreClass.Title)
            put(User_Data.Task, localDataStoreClass.Task)
        }
        db.insert(User_Data.Table_Name,null,values)
        db.close()
    }

    fun Delete_Task(id : Int){
        val db = this.writableDatabase
        db.delete(
            User_Data.Table_Name,
            "id = ?",
            arrayOf(id.toString())
        )
        db.close()
    }

    fun Update_Task(localDataStoreClassReturn: localDataStoreClassReturn){
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(User_Data.Title, localDataStoreClassReturn.Title)
            put(User_Data.Task, localDataStoreClassReturn.Task)
        }
        db.update(User_Data.Table_Name, values, "Id = ?", arrayOf(localDataStoreClassReturn.Id.toString()))
        db.close()
    }

    fun Get_Task() : List<localDataStoreClassReturn> {
        val db = this.readableDatabase
        val taskList = mutableListOf<localDataStoreClassReturn>()
        val cursor = db.query(
            User_Data.Table_Name,
            arrayOf(User_Data.key, User_Data.Title, User_Data.Task),
            null,
            null,
            null,
            null,
            null
        )
        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(User_Data.key))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(User_Data.Title))
                val task = cursor.getString(cursor.getColumnIndexOrThrow(User_Data.Task))

                val taskObj = localDataStoreClassReturn(title, task, id)
                taskList.add(taskObj)
            } while (cursor.moveToNext())
    }
        cursor.close()
        db.close()
        return taskList
}
}
