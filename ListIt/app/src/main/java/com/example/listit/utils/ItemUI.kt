package com.example.listit.utils

import android.R.attr.fontWeight
import android.R.attr.onClick
import android.R.attr.text
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listit.R
import com.example.listit.di.AppModule
import com.example.listit.ui.home.DeleteTask
import com.example.listit.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

class ItemUI() {
    @Composable
    fun ItemUIToShow(
        modifier: Modifier,
        currentTheme : String,
        title: String,
        task: String?,
        id : Int,
        viewModel: HomeViewModel,
        onEditClick : () -> Unit
    ) {
        val context = LocalContext.current
        val ThemeColorCurrent = ThemeColor(currentTheme)
        var showAllTextOptions by remember { mutableStateOf(false) }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    ThemeColorCurrent.containerColor
                )
                .clickable(
                    onClick = {
                        showAllTextOptions = !showAllTextOptions
                    }
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
           Column(
               modifier = Modifier.weight(1f).padding(start = 10.dp, bottom = 2.dp, top = 6.dp, end = if(showAllTextOptions)2.dp else 12.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center
           ) {
                Text(
                    style = TextStyle(
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center
                    ),
                    text = title,
                    color = ThemeColorCurrent.contentColor
                )
                if(showAllTextOptions){
                    if (task != null) {
                        Text(
                            style = TextStyle(
                                textAlign = TextAlign.Center
                            ),
                            text = task,
                            color = ThemeColorCurrent.contentColor
                        )
                    }
                }
            }
           if(showAllTextOptions){
               Row() {
                   IconButton(
                       colors = IconButtonDefaults.iconButtonColors(
                           contentColor = ThemeColorCurrent.contentColor,
                           containerColor = ThemeColorCurrent.containerColor
                       ),
                       onClick = {
                           onEditClick()
                       }
                   ) {
                       Icon(
                           Icons.Default.Edit,
                           contentDescription = "This is to edit the text"
                       )
                   }
                   IconButton(
                       colors = IconButtonDefaults.iconButtonColors(
                           contentColor = ThemeColorCurrent.contentColor,
                           containerColor = ThemeColorCurrent.containerColor
                       ),
                       onClick = {
                           DeleteTask(context = context, Id = id, viewModel = viewModel)
                       }
                   ) {
                       Icon(
                           Icons.Default.Delete,
                           contentDescription = "This is to delete the text"
                       )
                   }
               }
           }
        }
    }
}