package com.example.listall.database

import androidx.compose.ui.graphics.Color

data class DataType(val Task : String) {
}
data class TaskReturnDataType(val Task : String, val Id : Long)
data class ThemeType(val id : Long){}

data class Theme(val id : Long, val Theme_Name : String, val Color : Color, val contentColor : Color){}