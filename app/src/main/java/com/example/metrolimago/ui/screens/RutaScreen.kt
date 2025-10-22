package com.example.metrolimago.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.metrolimago.data.MetroDatabase
import com.example.metrolimago.model.Estacion
import com.example.metrolimago.ui.viewmodel.RutaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RutaScreen(navController: NavController) { // 👈 ahora recibe NavController
    val context = LocalContext.current
    val db = remember { MetroDatabase.getDatabase(context) }

    val viewModel: RutaViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RutaViewModel(db) as T
            }
        }
    )

    val estaciones by viewModel.estaciones.collectAsState()
    val resultado by viewModel.resultado.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Planificador de Ruta") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Selecciona tu origen y destino", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            DropdownEstacion(
                label = "Origen",
                estaciones = estaciones,
                onSelect = { viewModel.seleccionarOrigen(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DropdownEstacion(
                label = "Destino",
                estaciones = estaciones,
                onSelect = { viewModel.seleccionarDestino(it) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            resultado?.let {
                Text(it, style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 👇 Botón para regresar al Home
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Menú Principal")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownEstacion(
    label: String,
    estaciones: List<Estacion>,
    onSelect: (Estacion) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            estaciones.forEach { estacion ->
                DropdownMenuItem(
                    text = { Text(estacion.nombre) },
                    onClick = {
                        selectedText = estacion.nombre
                        onSelect(estacion)
                        expanded = false
                    }
                )
            }
        }
    }
}
