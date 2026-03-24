package com.example.listall.theme

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.listall.database.DataRepository
import com.example.listall.database.ThemeType
import androidx.compose.runtime.State
import androidx.compose.runtime.remember

class ThemeViewModel() : ViewModel() {
    private val _theme = DataRepository()
    private val _theme_id = mutableStateOf(1L)
    val theme_id : State<Long> = _theme_id
    fun LoadTheme(context: Context){
        val saved = _theme.ListTheme(context)
        _theme_id.value = saved?.id ?: 1L
    }
    fun InsertTheme(id : Long, context: Context){
        _theme.InsertTheme(context = context, ThemeType = ThemeType(id))
        _theme_id.value = id
    }
}