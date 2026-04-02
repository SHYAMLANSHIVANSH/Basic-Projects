package com.example.listit.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.listit.di.AppModule

    @Composable
    fun getTheCurrentTheme(context: Context) : String{
        val viewModel = remember {
            AppModule.provideThemeViewModel(context)
        }
        val currentTheme = viewModel.colorName.collectAsState()
        return currentTheme.value
    }