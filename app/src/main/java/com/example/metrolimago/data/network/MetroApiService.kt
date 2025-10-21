package com.example.metrolimago.data.network

import retrofit2.http.GET

// Esto debe coincidir con el formato del JSON remoto
data class Alerta(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val fecha: String
)

interface MetroApiService {
    // OJO: aquí NO va la URL completa, solo el "camino" relativo al baseUrl
    // Si tu archivo está en:
    // https://raw.githubusercontent.com/USUARIO/REPO/BRANCH/alertas.json
    // entonces el baseUrl será "https://raw.githubusercontent.com/"
    // y aquí va "USUARIO/REPO/BRANCH/alertas.json"
    @GET("yashira692/MetrolimaData/main/alertas.json")
    suspend fun getAlertas(): List<Alerta>
}
