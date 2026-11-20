package com.jesus.razasperros.di

import android.content.Context
import androidx.room.Room
import com.jesus.razasperros.data.local.RazasDatabase
import com.jesus.razasperros.data.remote.DogApiService
import com.jesus.razasperros.data.remote.RetrofitClient
import com.jesus.razasperros.data.repository.RazaRepositoryImpl
import com.jesus.razasperros.domain.repository.RazaRepository

/**
 * Inyección de dependencias (manual).
 * Aquí se crean UNA sola vez la base de datos, la API y el repositorio,
 * y se le entregan a quien los necesite (el ViewModel).
 */
interface AppContainer {
    val razaRepository: RazaRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    private val baseDeDatos: RazasDatabase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            RazasDatabase::class.java,
            RazasDatabase.NOMBRE
        ).build()
    }

    private val api: DogApiService by lazy { RetrofitClient.api }

    override val razaRepository: RazaRepository by lazy {
        RazaRepositoryImpl(api = api, db = baseDeDatos)
    }
}
