package com.example.metrolimago.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.metrolimago.data.MetroDatabase
import com.example.metrolimago.model.Estacion
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleEstacionScreen(
    navController: NavController,
    id: Int,
    db: MetroDatabase
) {
    var estacion by remember { mutableStateOf<Estacion?>(null) }
    val scope = rememberCoroutineScope()

    // Cargar la estación desde Room (por ID)
    LaunchedEffect(id) {
        estacion = db.estacionDao().getById(id)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(estacion?.nombre ?: "Detalle de Estación") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
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
            // Mostrar mensaje si no encuentra la estación
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Estación no encontrada", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
