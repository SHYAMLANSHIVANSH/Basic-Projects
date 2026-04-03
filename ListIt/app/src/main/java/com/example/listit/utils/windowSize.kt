import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass

class currentWindowSizeInfo{
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    @Composable
    fun getWindowSizeInfo(): WindowSizeClass {
        return currentWindowAdaptiveInfo().windowSizeClass
    }
}