package com.jesus.razasperros.data.remote

import retrofit2.http.GET

interface DogApiService {
    @GET("breeds")
    suspend fun obtenerRazas(): List<RazaDto>
}
