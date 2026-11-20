package com.jesus.razasperros.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Base de datos SQLite "razas_perros.db".
 * La instancia la crea el contenedor de dependencias (di/AppContainer).
 */
@Database(
    entities = [
        GrupoEntity::class,
        RazaEntity::class,
        TemperamentoEntity::class,
        RazaTemperamentoCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RazasDatabase : RoomDatabase() {

    abstract fun razaDao(): RazaDao

    companion object {
        const val NOMBRE = "razas_perros.db"
    }
}
