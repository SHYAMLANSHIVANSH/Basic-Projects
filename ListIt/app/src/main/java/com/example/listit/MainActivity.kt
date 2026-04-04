package com.example.listit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.listit.di.AppModule
import com.example.listit.navigation.AllDestinations
import com.example.listit.navigation.MainNavigationGraph
import com.example.listit.ui.theme.ListItTheme
import com.example.listit.utils.getTheCurrentTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val mainNavHostController = rememberNavController()
            ListItTheme {
                val context = LocalContext.current
                val homeViewModel = remember {
                    AppModule.provideLocalDataViewModel(context)
                }
                val currentColor = getTheCurrentTheme(context)

                MainNavigationGraph(
                    mainNavHostController,
                    AllDestinations.Home_Route,
                    homeViewModel,
                    windowSizeClass,
                    currentColor
                )
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ListItTheme {
            Greeting("Android")
        }
    }
}