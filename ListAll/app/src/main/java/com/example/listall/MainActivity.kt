package com.example.listall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.listall.ui.theme.ListAllTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListAllTheme {

                val windowSize : WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
                val navController : NavHostController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                when(windowSize.widthSizeClass){

                    WindowWidthSizeClass.Compact -> {
                        Scaffold(
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
                            Box(modifier = Modifier.padding(innerpadding)) {
                                NavigationGraph(navController)
                            }
                        }
                    }

                    WindowWidthSizeClass.Medium -> {
                        Row(){
                            DrawerVertical(
                                modifier = Modifier.fillMaxHeight(),
                                currentRoute,
                                Navigation(navController).NavigateToHome,
                                Navigation(navController).NavigateToStar,
                                Navigation(navController).NavigateToTheme
                            )

                            NavigationGraph(navController)
                        }
                    }

                    WindowWidthSizeClass.Expanded -> {
                        Row(){
                            DrawerCustom(
                                modifier = Modifier.fillMaxHeight(),
                                currentRoute,
                                Navigation(navController).NavigateToHome,
                                Navigation(navController).NavigateToStar,
                                Navigation(navController).NavigateToTheme
                            )

                            NavigationGraph(navController)
                        }
                    }

                    else -> {
                        Scaffold(
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
                           Box(modifier = Modifier.padding(innerpadding)) {
                                NavigationGraph(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

