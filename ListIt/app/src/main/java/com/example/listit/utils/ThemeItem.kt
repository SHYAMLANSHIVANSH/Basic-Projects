package com.example.listit.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listit.ui.uiThemes.AllThemes

class ThemeItem {


    @SuppressLint("NotConstructor")
    @Composable
    fun ThemeItemCardBox(
        color : String,
        currentTheme : String,
        onClick : () -> Unit
    ){
        val theme =  ThemeColor(color)

        Box(){
            Card(
                colors = CardDefaults.cardColors(
                    contentColor = theme.contentColor,
                    containerColor = theme.containerColor
                ),
                modifier = Modifier
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(
                        onClick = { onClick() }
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(color)
                }
            }
            
            if(currentTheme == color){
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .align(Alignment.TopEnd)
                        .background(Color.Green)
                ){
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "This shows which theme is selected",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ThemeItemUiTest(){
    val theme = ThemeItem()
    theme.ThemeItemCardBox(
        color = AllThemes.Theme1,
        currentTheme = AllThemes.Theme1,
        onClick = {}
    )
}