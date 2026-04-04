package com.example.listit.navigation

import android.R.attr.type
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.listit.ui.home.AddItems
import com.example.listit.ui.home.EditItems
import com.example.listit.ui.home.HomeContent
import com.example.listit.ui.home.HomeViewModel

object HomeInnerAllDestinations{
    const val List_Route = "Home_List"
    const val Add_Route = "Add_Task"
    const val Edit_Route = "Edit_Task"
}

@Composable
fun HomeInnerNavGraph(
    navController: NavHostController,
    viewModel: HomeViewModel
){
    NavHost(
        navController = navController,
        startDestination = HomeInnerAllDestinations.List_Route
    ){
        composable(HomeInnerAllDestinations.List_Route) {
            HomeContent(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate(HomeInnerAllDestinations.Add_Route)
                },
                onEditClick = {id ->
                    navController.navigate("${HomeInnerAllDestinations.Edit_Route}/$id")
                    Log.d("HomeInnerNavGraph", "$id has been passed from here")
                }
                )
        }

        composable(HomeInnerAllDestinations.Add_Route) {
            AddItems(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(HomeInnerAllDestinations.Edit_Route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )) {backStackEntry->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            EditItems(
                viewModel = viewModel,
                id = id,
                onBack = {
                    navController.popBackStack()
                }
            )
            Log.d("Composable Edit", "$id has been passed from here")
        }
    }
}