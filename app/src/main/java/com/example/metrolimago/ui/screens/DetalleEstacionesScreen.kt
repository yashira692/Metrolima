package com.example.metrolimago.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.metrolimago.data.EstacionDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleEstacionScreen(navController: NavController, id: Int) {
    val estacion = EstacionDao.getById(id)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(estacion?.nombre ?: "Detalle de Estación") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        }
    ) { padding ->
        estacion?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Línea: ${it.linea}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.descripcion,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } ?: run {
            // Si no encuentra la estación (por seguridad)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Estación no encontrada", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

