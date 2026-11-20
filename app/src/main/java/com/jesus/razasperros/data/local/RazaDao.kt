package com.jesus.razasperros.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface RazaDao {

    // ---------- Consultas ----------

    @Transaction
    @Query("SELECT * FROM raza ORDER BY nombre")
    suspend fun obtenerRazas(): List<RazaCompleta>

    @Transaction
    @Query("SELECT * FROM raza WHERE id_raza = :idRaza")
    suspend fun obtenerRaza(idRaza: Int): RazaCompleta?

    @Transaction
    @Query(
        """
        SELECT r.* FROM raza r
        INNER JOIN grupo g ON g.id_grupo = r.id_grupo
        WHERE g.nombre = :nombreGrupo
        ORDER BY r.nombre
        """
    )
    suspend fun obtenerRazasPorGrupo(nombreGrupo: String): List<RazaCompleta>

    @Query("SELECT COUNT(*) FROM raza")
    suspend fun contarRazas(): Int

    // ---------- Grupos ----------

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarGrupo(grupo: GrupoEntity): Long

    @Query("SELECT id_grupo FROM grupo WHERE nombre = :nombre")
    suspend fun idGrupo(nombre: String): Int?

    // ---------- Temperamentos ----------

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarTemperamento(temperamento: TemperamentoEntity): Long

    @Query("SELECT id_temperamento FROM temperamento WHERE nombre = :nombre")
    suspend fun idTemperamento(nombre: String): Int?

    // ---------- Razas ----------

    @Upsert
    suspend fun guardarRaza(raza: RazaEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarRazaTemperamento(ref: RazaTemperamentoCrossRef)

    @Query("DELETE FROM raza")
    suspend fun borrarRazas()

    @Query("DELETE FROM raza_temperamento WHERE id_raza = :idRaza")
    suspend fun borrarTemperamentosDeRaza(idRaza: Int)

    /** Devuelve el id del grupo; lo crea si no existe. */
    @Transaction
    suspend fun obtenerOCrearGrupo(nombre: String): Int {
        idGrupo(nombre)?.let { return it }
        insertarGrupo(GrupoEntity(nombre = nombre))
        return idGrupo(nombre)!!
    }

    /** Devuelve el id del temperamento; lo crea si no existe. */
    @Transaction
    suspend fun obtenerOCrearTemperamento(nombre: String): Int {
        idTemperamento(nombre)?.let { return it }
        insertarTemperamento(TemperamentoEntity(nombre = nombre))
        return idTemperamento(nombre)!!
    }
}
