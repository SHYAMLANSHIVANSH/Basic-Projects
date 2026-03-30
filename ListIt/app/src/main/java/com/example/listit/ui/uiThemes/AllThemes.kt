package com.example.listit.ui.uiThemes

import com.example.listit.utils.ThemeItem

object AllThemes {
    const val Theme1 : String = "BLUE"
    const val Theme2 : String = "GREEN"
    const val Theme3 : String = "PEACH"
    const val Theme4 : String = "LAVENDER"
    const val Theme5 : String = "YELLOW"
    const val Theme6 : String = "GREY"
}

class getAllThemesList(){
    val LoadThemesList : List<String> = listOf(
        AllThemes.Theme1, AllThemes.Theme2, AllThemes.Theme3, AllThemes.Theme4, AllThemes.Theme5,
        AllThemes.Theme6
    )
}
