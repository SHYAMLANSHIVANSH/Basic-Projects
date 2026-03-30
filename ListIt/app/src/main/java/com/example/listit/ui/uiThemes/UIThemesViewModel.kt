package com.example.listit.ui.uiThemes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listit.data.ThemeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UIThemesViewModel(
    private val repository: ThemeRepository
) : ViewModel() {

    val colorName : StateFlow<String> = repository.themeColor
        .stateIn(
            scope = viewModelScope,
            started =
                SharingStarted.WhileSubscribed(1000),
            initialValue = AllThemes.Theme1
        )

    fun saveTheme(color : String){
        viewModelScope.launch {
            repository.saveThemeColor(color)
        }
    }

}