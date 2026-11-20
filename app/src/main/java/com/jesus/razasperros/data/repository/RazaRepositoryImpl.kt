package com.jesus.razasperros.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.jesus.razasperros.data.local.DatosIniciales
import com.jesus.razasperros.data.local.RazaTemperamentoCrossRef
import com.jesus.razasperros.data.local.RazasDatabase
import com.jesus.razasperros.data.mapper.nombresTemperamentos
import com.jesus.razasperros.data.mapper.toDomain
import com.jesus.razasperros.data.mapper.toEntity
import com.jesus.razasperros.data.remote.DogApiService
import com.jesus.razasperros.data.remote.RazaDto
import com.jesus.razasperros.domain.model.Raza
import com.jesus.razasperros.domain.repository.RazaRepository

/**
 * Implementación del repositorio (capa de datos).
 * 1. Intenta descargar las razas de TheDogAPI y las guarda en SQLite (Room).
 * 2. Siempre devuelve lo que hay en la base de datos, así la app funciona sin internet.
 */
class RazaRepositoryImpl(
    private val api: DogApiService,
    private val db: RazasDatabase
) : RazaRepository {

    private val dao = db.razaDao()

    override suspend fun obtenerRazas(): List<Raza> {
        try {
            val remotas = api.obtenerRazas()
            guardarEnBaseDeDatos(remotas, reemplazarTodo = true)
        } catch (e: Exception) {
            // Sin internet o la API rechazó la petición (p. ej. HTTP 403 sin API key)
            Log.w("RazasPerros", "API no disponible, usando base de datos local", e)
            if (dao.contarRazas() == 0) {
                guardarEnBaseDeDatos(DatosIniciales.razas, reemplazarTodo = false)
            }
        }
        return dao.obtenerRazas().map { it.toDomain() }
    }

    override suspend fun obtenerRaza(id: Int): Raza? =
        dao.obtenerRaza(id)?.toDomain()

    private suspend fun guardarEnBaseDeDatos(razas: List<RazaDto>, reemplazarTodo: Boolean) {
        db.withTransaction {
            if (reemplazarTodo) dao.borrarRazas()
            for (dto in razas) {
                val idGrupo = dto.breedGroup
                    ?.trim()
                    ?.takeIf { it.isNotEmpty() }
                    ?.let { dao.obtenerOCrearGrupo(it) }

                dao.guardarRaza(dto.toEntity(idGrupo))

                dao.borrarTemperamentosDeRaza(dto.id)
                dto.nombresTemperamentos().forEach { nombre ->
                    val idTemp = dao.obtenerOCrearTemperamento(nombre)
                    dao.insertarRazaTemperamento(RazaTemperamentoCrossRef(dto.id, idTemp))
                }
            }
        }
    }
}
