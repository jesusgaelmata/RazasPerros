package com.jesus.razasperros.domain.repository

import com.jesus.razasperros.domain.model.Raza

/**
 * Contrato del repositorio (capa de dominio).
 * El ViewModel solo conoce esta interfaz, no sabe si los datos
 * vienen de Room o de Retrofit.
 */
interface RazaRepository {
    suspend fun obtenerRazas(): List<Raza>
    suspend fun obtenerRaza(id: Int): Raza?
}
