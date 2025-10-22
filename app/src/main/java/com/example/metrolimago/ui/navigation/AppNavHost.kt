package com.example.metrolimago.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.metrolimago.ui.screens.*
import com.example.metrolimago.data.MetroDatabase

@Composable
fun AppNavHost(navController: NavHostController, db: MetroDatabase) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("lista") { ListaEstacionesScreen(navController, db) }
        composable("alertas") { AlertasScreen(navController) }

        composable("ruta") { RutaScreen(navController) }



        composable("detalle/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetalleEstacionScreen(navController, id, db)
        }
    }
}
