package com.example.metrolimago.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metrolimago.data.network.Alerta
import com.example.metrolimago.data.repository.MetroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlertasViewModel : ViewModel() {
    private val repo = MetroRepository()

    private val _alertas = MutableStateFlow<List<Alerta>>(emptyList())
    val alertas: StateFlow<List<Alerta>> = _alertas

    fun cargarAlertas() {
        viewModelScope.launch {
            try {
                _alertas.value = repo.getAlertas()
            } catch (e: Exception) {
                // Si algo falla (sin internet, 404, etc.)
                e.printStackTrace()
                _alertas.value = emptyList()
            }
        }
    }
}
