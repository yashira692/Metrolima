package com.example.metrolimago.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metrolimago.data.MetroDatabase
import com.example.metrolimago.model.Estacion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RutaViewModel(private val db: MetroDatabase) : ViewModel() {

    private val _estaciones = MutableStateFlow<List<Estacion>>(emptyList())
    val estaciones: StateFlow<List<Estacion>> = _estaciones

    private val _origen = MutableStateFlow<Estacion?>(null)
    val origen: StateFlow<Estacion?> = _origen

    private val _destino = MutableStateFlow<Estacion?>(null)
    val destino: StateFlow<Estacion?> = _destino

    private val _resultado = MutableStateFlow<String?>(null)
    val resultado: StateFlow<String?> = _resultado

    init {
        cargarEstaciones()
    }

    private fun cargarEstaciones() {
        viewModelScope.launch {
            _estaciones.value = db.estacionDao().getAll()
        }
    }

    fun seleccionarOrigen(estacion: Estacion) {
        _origen.value = estacion
        calcularRuta()
    }

    fun seleccionarDestino(estacion: Estacion) {
        _destino.value = estacion
        calcularRuta()
    }

    private fun calcularRuta() {
        val ori = _origen.value
        val dest = _destino.value
        val lista = _estaciones.value.sortedBy { it.id }

        if (ori != null && dest != null && lista.isNotEmpty()) {
            val i1 = lista.indexOfFirst { it.id == ori.id }
            val i2 = lista.indexOfFirst { it.id == dest.id }

            if (i1 >= 0 && i2 >= 0) {
                val rango = if (i1 <= i2) i1..i2 else i2..i1
                val recorrido = lista.slice(rango).map { it.nombre }
                val tiempo = recorrido.size * 2 // 2 min por estación aprox.

                _resultado.value =
                    "⏱️ Tiempo estimado: $tiempo minutos\n\n🚉 Estaciones:\n${recorrido.joinToString(" → ")}"
            }
        }
    }
}

