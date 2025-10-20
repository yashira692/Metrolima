package com.example.metrolimago.data

import androidx.room.*
import com.example.metrolimago.model.Estacion

@Dao
interface EstacionDao {
    @Query("SELECT * FROM estaciones ORDER BY id ASC")
    suspend fun getAll(): List<Estacion>

    @Query("SELECT * FROM estaciones WHERE id = :id")
    suspend fun getById(id: Int): Estacion?

    @Query("SELECT * FROM estaciones WHERE nombre LIKE '%' || :query || '%' OR linea LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<Estacion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(estaciones: List<Estacion>)
}
