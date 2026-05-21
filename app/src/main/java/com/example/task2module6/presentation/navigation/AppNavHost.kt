package com.example.task2module6.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.task2module6.presentation.screens.NobelDetailScreen
import com.example.task2module6.presentation.screens.NobelListScreen

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("detail/{year}/{category}") {
        fun createRoute(year: String, category: String) = "detail/$year/$category"
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            NobelListScreen(
                onPrizeClick = { year, category ->
                    navController.navigate(Screen.Detail.createRoute(year, category))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("year") { type = NavType.StringType },
                navArgument("category") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val year = backStackEntry.arguments?.getString("year") ?: ""
            val category = backStackEntry.arguments?.getString("category") ?: ""
            NobelDetailScreen(year = year, category = category, onBack = { navController.popBackStack() })
        }
    }
}
