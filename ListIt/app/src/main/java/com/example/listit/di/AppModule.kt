package com.example.listit.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.listit.data.ThemePreferenceDataSource
import com.example.listit.data.ThemeRepository
import com.example.listit.data.dataStore
import com.example.listit.ui.uiThemes.UIThemesViewModel

object AppModule {
    fun provideThemeViewModel(context: Context) : UIThemesViewModel{
        val dataStore = context.dataStore

        val dataSource = ThemePreferenceDataSource(dataStore)

        val repository = ThemeRepository(dataSource)

        return UIThemesViewModel(repository)
    }
}