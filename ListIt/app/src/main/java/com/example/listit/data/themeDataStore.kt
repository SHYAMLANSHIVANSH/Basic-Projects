package com.example.listit.data

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.listit.data.themeDataStore.SELECTED_COLOR
import com.example.listit.ui.uiThemes.AllThemes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "user_settings")

object themeDataStore {

    val SELECTED_COLOR = stringPreferencesKey("selected_color")
}

class ThemePreferenceDataSource(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveColor(color: String) {
        dataStore.edit { preferences ->
            preferences[SELECTED_COLOR] = color
        }
    }

    val getColor: Flow<String> =
        dataStore.data
            .map { preferences ->
                preferences[SELECTED_COLOR] ?: AllThemes.Theme1
    }
}