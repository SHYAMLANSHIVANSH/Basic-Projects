package com.example.listit.data

import android.content.Context
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow

class ThemeRepository(private val dataSourceManager: ThemePreferenceDataSource) {

    suspend fun saveThemeColor(color : String){
        dataSourceManager.saveColor(color)
    }

    val themeColor : Flow<String> = dataSourceManager.getColor
}