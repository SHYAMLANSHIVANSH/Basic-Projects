package com.example.listit.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.listit.ui.uiThemes.AllThemes

class ThemeColor(color : String) {

    var contentColor : Color = Color(0xFF0B3C91)
    var containerColor : Color = Color(0xFFBBDEFB)

    init{

        if (color == AllThemes.Theme1) {
            contentColor = Color(0xFF0B3C91)
            containerColor = Color(0xFFBBDEFB)
        }
        else if(color == AllThemes.Theme2){
            contentColor = Color(0xFF1B5E20)
            containerColor = Color(0xFFC8E6C9)
        }
        else if(color == AllThemes.Theme3){
            contentColor = Color(0xFF7A1F00)
            containerColor = Color(0xFFFFCCBC)
        }
        else if(color == AllThemes.Theme4){
            contentColor = Color(0xFF3A0A6B)
            containerColor = Color(0xFFE1BEE7)
        }
        else if(color == AllThemes.Theme5){
            contentColor = Color(0xFF3E2723)
            containerColor = Color(0xFFFFF9C4)
        }
        else if(color == AllThemes.Theme6){
            contentColor = Color(0xFF111111)
            containerColor = Color(0xFFE0E0E0)
        }
    }
}

