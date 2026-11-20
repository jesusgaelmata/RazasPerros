package com.jesus.razasperros

import android.app.Application
import com.jesus.razasperros.di.AppContainer
import com.jesus.razasperros.di.DefaultAppContainer

/**
 * Clase Application: se crea al abrir la app y guarda el contenedor
 * de dependencias para que viva mientras la app esté abierta.
 * (Registrada en AndroidManifest.xml con android:name=".RazasPerrosApp")
 */
class RazasPerrosApp : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
