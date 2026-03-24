package com.example.listall.theme

import androidx.compose.ui.graphics.Color
import com.example.listall.database.Theme

class AllThemes() {
    val Themes = listOf(
        Theme(0, "Blue Light", Color(0xFFBBDEFB), Color(0xFF0B3C91)),   // Slightly deeper blue
        Theme(1, "Green Light", Color(0xFFC8E6C9), Color(0xFF1B5E20)),  // Already perfect
        Theme(2, "Peach", Color(0xFFFFCCBC), Color(0xFF7A1F00)),        // Much darker for contrast
        Theme(3, "Lavender", Color(0xFFE1BEE7), Color(0xFF3A0A6B)),     // Slightly deeper purple
        Theme(4, "Yellow Soft", Color(0xFFFFF9C4), Color(0xFF3E2723)),  // FIXED: dark brown (very readable)
        Theme(5, "Grey Neutral", Color(0xFFE0E0E0), Color(0xFF111111))  // Stronger than before
    )
}