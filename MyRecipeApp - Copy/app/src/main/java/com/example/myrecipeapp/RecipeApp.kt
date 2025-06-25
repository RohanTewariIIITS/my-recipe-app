package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel:MainViewModel = viewModel()
    val viewState by recipeViewModel.categoryState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable (route = Screen.RecipeScreen.route){
            RecipeScreen(
                navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("category",it)
                    navController.navigate(Screen.CategoryDetailScreen.route)
                },
                viewstate = viewState
            )
        }
        composable (Screen.CategoryDetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")?: Category("","","","")
            CategoryDetailScreen(category = category)
        }
    }
}