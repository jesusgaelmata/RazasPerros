package com.jesus.razasperros.data.mapper

import com.jesus.razasperros.data.local.RazaCompleta
import com.jesus.razasperros.data.local.RazaEntity
import com.jesus.razasperros.data.remote.RazaDto
import com.jesus.razasperros.domain.model.Raza

/** API (DTO) -> Base de datos (Entity) */
fun RazaDto.toEntity(idGrupo: Int?): RazaEntity = RazaEntity(
    idRaza = id,
    nombre = name,
    criadoPara = bredFor,
    esperanzaVida = lifeSpan,
    pesoKg = weight?.metric,
    pesoLb = weight?.imperial,
    alturaCm = height?.metric,
    alturaIn = height?.imperial,
    origen = origin,
    idImagen = referenceImageId,
    idGrupo = idGrupo
)

/** "Friendly, Loyal, Playful" -> ["Friendly", "Loyal", "Playful"] */
fun RazaDto.nombresTemperamentos(): List<String> =
    temperament
        ?.split(",")
        ?.map { it.trim() }
        ?.filter { it.isNotEmpty() }
        ?.distinct()
        .orEmpty()

/** Base de datos (Entity + relaciones) -> Dominio */
fun RazaCompleta.toDomain(): Raza = Raza(
    id = raza.idRaza,
    nombre = raza.nombre,
    grupo = grupo?.nombre,
    criadoPara = raza.criadoPara,
    esperanzaVida = raza.esperanzaVida,
    temperamentos = temperamentos.map { it.nombre },
    origen = raza.origen,
    pesoKg = raza.pesoKg,
    alturaCm = raza.alturaCm
)
