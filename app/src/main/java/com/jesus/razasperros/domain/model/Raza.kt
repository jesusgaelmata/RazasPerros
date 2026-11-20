package com.jesus.razasperros.domain.model

/**
 * Modelo de dominio: así ve la app una raza, sin importar si viene
 * de la API (RazaDto) o de la base de datos (RazaEntity).
 */
data class Raza(
    val id: Int,
    val nombre: String,
    val grupo: String?,
    val criadoPara: String?,
    val esperanzaVida: String?,
    val temperamentos: List<String>,
    val origen: String?,
    val pesoKg: String?,
    val alturaCm: String?
)
