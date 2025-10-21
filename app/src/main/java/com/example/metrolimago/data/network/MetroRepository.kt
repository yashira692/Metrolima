package com.example.metrolimago.data.repository

import com.example.metrolimago.data.network.RetrofitClient

class MetroRepository {
    private val api = RetrofitClient.api

    suspend fun getAlertas() = api.getAlertas()
}
