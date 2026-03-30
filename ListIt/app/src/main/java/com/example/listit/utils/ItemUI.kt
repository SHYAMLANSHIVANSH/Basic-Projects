package com.example.listit.utils

import android.R.attr.onClick
import android.R.attr.text
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listit.R

class ItemUI {
    @Composable
    fun ItemUIToShow(modifier: Modifier) {
        var showAllTextOptions by remember { mutableStateOf(false) }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    if (isSystemInDarkTheme()) Color.White
                    else Color.White
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
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center
           ) {
                Text(
                    text = "Title"
                )
                Text(
                    text = "This is a text"
                )
            }
           if(showAllTextOptions){
               Spacer(Modifier.weight(1f))
               Row() {
                   IconButton(
                       onClick = {}
                   ) {
                       Icon(
                           Icons.Default.Edit,
                           contentDescription = "This is to edit the text"
                       )
                   }
                   IconButton(
                       onClick = {}
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


@Preview
@Composable
fun TestProfilePictureFunction(ItemUI : ItemUI = ItemUI()){
    ItemUI.ItemUIToShow(modifier = Modifier.fillMaxWidth())

}