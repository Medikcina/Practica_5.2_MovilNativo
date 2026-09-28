package com.example.practica_5_2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practica_5_2.screens.DetailScreen
import com.example.practica_5_2.screens.FormScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "form") {
        composable("form") {
            FormScreen(navController)
        }

        composable(
            route = "detail/{matricula}/{nombre}/{carrera}/{turno}/{estatus}",
            arguments = listOf(
                navArgument("matricula") { type = NavType.StringType },
                navArgument("nombre") { type = NavType.StringType },
                navArgument("carrera") { type = NavType.StringType },
                navArgument("turno") { type = NavType.StringType },
                navArgument("estatus") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                matricula = backStackEntry.arguments?.getString("matricula") ?: "",
                nombre = backStackEntry.arguments?.getString("nombre") ?: "",
                carrera = backStackEntry.arguments?.getString("carrera") ?: "",
                turno = backStackEntry.arguments?.getString("turno") ?: "",
                estatus = backStackEntry.arguments?.getBoolean("estatus") ?: false
            )
        }
    }
}