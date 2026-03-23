package com.example.listall.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.renderscript.Sampler

private const val Database_Name = "ListData"
private const val Version = 1
private const val task = "Task"
private const val id = "id"
private const val Database_TaskTableName = "ListData"

private const val Database_StarTableName = "StarData"

private const val Database_ThemeTableName = "StarData"

private const val Colorid = "id"
class Database(context: Context) : SQLiteOpenHelper(context, Database_Name, null, Version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableForTask = """
            CREATE TABLE $Database_TaskTableName(
            $id INTEGER PRIMARY KEY AUTOINCREMENT,
            $task TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(createTableForTask)

        val createTableForStarTask = """
            CREATE TABLE $Database_StarTableName(
            $id INTEGER PRIMARY KEY AUTOINCREMENT,
            $task TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(createTableForStarTask)

        val createTableForTheme = """
            CREATE TABLE $Database_ThemeTableName(
            $Colorid INTEGER PRIMARY KEY
            )
        """.trimIndent()

        db?.execSQL(createTableForTheme)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }
// This is for the task table
    fun Insert(Values : DataType){
        val db = this.writableDatabase
        val value = ContentValues().apply {
            put(task, Values.Task )
        }
        db.insert(Database_TaskTableName, null, value)
        db.close()
    }
    fun Update(Values: DataType){
        val db = this.writableDatabase
        val value = ContentValues().apply {
            put(task, Values.Task )
        }
        db.update(Database_TaskTableName, value,"ID = ?", arrayOf(Values.id.toString())
        )
        db.close()
    }
    fun Delete(id : Long){
        val db = this.writableDatabase
        db.delete(Database_TaskTableName, "ID = ?", arrayOf(id.toString()))
        db.close()
    }
    fun Get(): Array<DataType> {
        val db = this.readableDatabase
        val cursor = db.query(
            Database_TaskTableName,
            arrayOf(id, task),
            null,
            null,
            null,
            null,
            null
        )

        val dataList = mutableListOf<DataType>()

        if (cursor.moveToFirst()) {
            do {
                val itemId = cursor.getLong(cursor.getColumnIndexOrThrow(id))
                val itemTask = cursor.getString(cursor.getColumnIndexOrThrow(task))
                val dataItem = DataType(itemId, itemTask)
                dataList.add(dataItem)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return dataList.toTypedArray()
    }
// This is for the theme
    fun InsertTheme(ThemeType : ThemeType){
        val db = this.writableDatabase

        db.delete(Database_ThemeTableName, null, null)

        val values = ContentValues().apply {
            put(Colorid, ThemeType.id)
        }
        db.insert(Database_ThemeTableName, null, values)
        db.close()
    }
    fun getTheme(): ThemeType? {
        val db = this.readableDatabase
        val cursor = db.query(
            Database_ThemeTableName,
            arrayOf(Colorid), // only the columns you need
            null,
            null,
            null,
            null,
            null
        )

        val theme: ThemeType? = if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(Colorid))
            ThemeType(id.toLong())
        } else {
            ThemeType(1)
        }

        cursor.close()
        db.close()
        return theme
    }
//This is for the star
    fun InsertStar(Values: DataType){
        val db = this.writableDatabase
        val value = ContentValues().apply {
          put(task, Values.Task )
    }
        db.insert(Database_StarTableName, null, value)
        db.close()
}
    fun DeleteStar(id : Long){
        val db = this.writableDatabase
        db.delete(Database_StarTableName, "ID = ?", arrayOf(id.toString()))
        db.close()
    }
    fun GetStar(): Array<DataType> {
        val db = this.readableDatabase
        val cursor = db.query(
            Database_StarTableName,
            arrayOf(id, task),
            null,
            null,
            null,
            null,
            null
        )

        val dataList = mutableListOf<DataType>()

        if (cursor.moveToFirst()) {
            do {
                val itemId = cursor.getLong(cursor.getColumnIndexOrThrow(id))
                val itemTask = cursor.getString(cursor.getColumnIndexOrThrow(task))
                val dataItem = DataType(itemId, itemTask)
                dataList.add(dataItem)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return dataList.toTypedArray()
    }
}