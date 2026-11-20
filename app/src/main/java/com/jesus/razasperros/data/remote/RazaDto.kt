package com.jesus.razasperros.data.remote

import com.google.gson.annotations.SerializedName

// Respuesta de TheDogAPI: GET https://api.thedogapi.com/v1/breeds
data class RazaDto(
    val id: Int,
    val name: String,
    @SerializedName("bred_for") val bredFor: String?,
    @SerializedName("breed_group") val breedGroup: String?,
    @SerializedName("life_span") val lifeSpan: String?,
    val temperament: String?,
    val origin: String?,
    val weight: MedidaDto?,
    val height: MedidaDto?,
    @SerializedName("reference_image_id") val referenceImageId: String?
)

data class MedidaDto(
    val imperial: String?,
    val metric: String?
)
