package com.example.metrolimago.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.metrolimago.ui.viewmodel.AlertasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertasScreen(navController: NavController) { // 👈 recibe NavController
    val viewModel: AlertasViewModel = viewModel()
    val alertas by viewModel.alertas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarAlertas()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Alertas del Metro de Lima") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            if (alertas.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Cargando alertas...")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    items(alertas) { alerta ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(Modifier.padding(16.dp)) {
                                Text(alerta.titulo, style = MaterialTheme.typography.titleMedium)
                                Spacer(Modifier.height(4.dp))
                                Text(alerta.descripcion, style = MaterialTheme.typography.bodyMedium)
                                Spacer(Modifier.height(4.dp))
                                Text("Fecha: ${alerta.fecha}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }

            // 👇 Botón para regresar al menú principal
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Volver al Menú Principal")
            }
        }
    }
}
