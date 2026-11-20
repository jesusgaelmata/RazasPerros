package com.jesus.razasperros.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

// Tabla GRUPO: Hound, Toy, Working, Sporting...
@Entity(
    tableName = "grupo",
    indices = [Index(value = ["nombre"], unique = true)]
)
data class GrupoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_grupo") val idGrupo: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String
)

// Tabla RAZA (1 grupo : N razas)
@Entity(
    tableName = "raza",
    foreignKeys = [
        ForeignKey(
            entity = GrupoEntity::class,
            parentColumns = ["id_grupo"],
            childColumns = ["id_grupo"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("id_grupo")]
)
data class RazaEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_raza") val idRaza: Int,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "criado_para") val criadoPara: String?,
    @ColumnInfo(name = "esperanza_vida") val esperanzaVida: String?,
    @ColumnInfo(name = "peso_kg") val pesoKg: String?,
    @ColumnInfo(name = "peso_lb") val pesoLb: String?,
    @ColumnInfo(name = "altura_cm") val alturaCm: String?,
    @ColumnInfo(name = "altura_in") val alturaIn: String?,
    @ColumnInfo(name = "origen") val origen: String?,
    @ColumnInfo(name = "id_imagen") val idImagen: String?,
    @ColumnInfo(name = "id_grupo") val idGrupo: Int?
)

// Tabla TEMPERAMENTO: Friendly, Loyal, Playful...
@Entity(
    tableName = "temperamento",
    indices = [Index(value = ["nombre"], unique = true)]
)
data class TemperamentoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_temperamento") val idTemperamento: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String
)

// Tabla intermedia RAZA_TEMPERAMENTO (N razas : M temperamentos)
@Entity(
    tableName = "raza_temperamento",
    primaryKeys = ["id_raza", "id_temperamento"],
    foreignKeys = [
        ForeignKey(
            entity = RazaEntity::class,
            parentColumns = ["id_raza"],
            childColumns = ["id_raza"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TemperamentoEntity::class,
            parentColumns = ["id_temperamento"],
            childColumns = ["id_temperamento"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("id_temperamento")]
)
data class RazaTemperamentoCrossRef(
    @ColumnInfo(name = "id_raza") val idRaza: Int,
    @ColumnInfo(name = "id_temperamento") val idTemperamento: Int
)

// Consulta con JOIN: una raza con su grupo y sus temperamentos
data class RazaCompleta(
    @Embedded val raza: RazaEntity,
    @Relation(
        parentColumn = "id_grupo",
        entityColumn = "id_grupo"
    )
    val grupo: GrupoEntity?,
    @Relation(
        parentColumn = "id_raza",
        entityColumn = "id_temperamento",
        associateBy = Junction(
            value = RazaTemperamentoCrossRef::class,
            parentColumn = "id_raza",
            entityColumn = "id_temperamento"
        )
    )
    val temperamentos: List<TemperamentoEntity>
)
