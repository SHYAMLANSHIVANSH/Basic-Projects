package com.example.listall.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Home(){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    Icons.Default.AddCircle,
                    contentDescription = "This is to add items"
                )
            }
        }
    ){innerpadding->

    }
}