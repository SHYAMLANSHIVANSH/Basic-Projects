package com.example.listall

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.listall.database.DataRepository
import com.example.listall.database.ThemeType
import com.example.listall.theme.ThemeViewModel
import com.example.listall.ui.theme.ListAllTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val themeViewModel : ThemeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            androidx.compose.runtime.LaunchedEffect(Unit) {
                themeViewModel.LoadTheme(context)
            }
            val currentTheme by themeViewModel.theme_id
            ListAllTheme() {
                val windowSize : WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
                val navController : NavHostController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route
                when(windowSize.widthSizeClass){
                    WindowWidthSizeClass.Compact -> {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            bottomBar = {
                                DrawerHorizontal(
                                    modifier = Modifier.fillMaxHeight(),
                                    currentRoute,
                                    Navigation(navController).NavigateToHome,
                                    Navigation(navController).NavigateToStar,
                                    Navigation(navController).NavigateToTheme
                                )
                            }
                        ){innerpadding->
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .padding(innerpadding)
                                .padding(start = 6.dp)
                            ) {
                                NavigationGraph(navController)
                            }
                        }
                    }

                    WindowWidthSizeClass.Medium -> {
                        Scaffold(
                            modifier = Modifier.fillMaxSize()
                        ){innerpadding->
                            Row(modifier = Modifier.fillMaxSize()) {
                                DrawerVertical(
                                    modifier = Modifier.fillMaxHeight(),
                                    currentRoute,
                                    Navigation(navController).NavigateToHome,
                                    Navigation(navController).NavigateToStar,
                                    Navigation(navController).NavigateToTheme
                                )

                                Box(modifier = Modifier.padding(innerpadding).padding(start = 6.dp)){
                                    NavigationGraph(navController)
                                }
                            }
                        }
                    }

                    WindowWidthSizeClass.Expanded -> {
                        Scaffold(modifier = Modifier.fillMaxSize()){innerpadding->
                            Row(modifier = Modifier.fillMaxSize()) {
                                DrawerCustom(
                                    modifier = Modifier.fillMaxHeight(),
                                    currentRoute,
                                    Navigation(navController).NavigateToHome,
                                    Navigation(navController).NavigateToStar,
                                    Navigation(navController).NavigateToTheme
                                )

                                Box(modifier = Modifier.padding(innerpadding).padding(start = 6.dp)){
                                    NavigationGraph(navController)
                                }
                            }
                        }
                    }

                    else -> {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            bottomBar = {
                                DrawerHorizontal(
                                    modifier = Modifier.fillMaxHeight(),
                                    currentRoute,
                                    Navigation(navController).NavigateToHome,
                                    Navigation(navController).NavigateToStar,
                                    Navigation(navController).NavigateToTheme
                                )
                            }
                        ){innerpadding->
                           Box(modifier = Modifier.fillMaxSize().padding(innerpadding).padding(start = 6.dp)) {
                                NavigationGraph(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

