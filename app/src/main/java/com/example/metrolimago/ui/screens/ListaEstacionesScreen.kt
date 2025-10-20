package com.example.metrolimago.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.metrolimago.data.MetroDatabase
import com.example.metrolimago.model.Estacion
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaEstacionesScreen(navController: NavController, db: MetroDatabase) {
    var estaciones by remember { mutableStateOf<List<Estacion>>(emptyList()) }
    var filtro by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        estaciones = db.estacionDao().getAll()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Estaciones") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(8.dp)) {
            OutlinedTextField(
                value = filtro,
                onValueChange = {
                    filtro = it
                    scope.launch {
                        estaciones = if (filtro.isBlank())
                            db.estacionDao().getAll()
                        else
                            db.estacionDao().search(filtro)
                    }
                },
                label = { Text("Buscar estación o línea") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            LazyColumn {
                items(estaciones) { estacion ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { navController.navigate("detalle/${estacion.id}") },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(text = estacion.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(text = estacion.linea, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
