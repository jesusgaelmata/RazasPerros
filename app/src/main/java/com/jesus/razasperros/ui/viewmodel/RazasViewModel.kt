package com.jesus.razasperros.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jesus.razasperros.RazasPerrosApp
import com.jesus.razasperros.domain.model.Raza
import com.jesus.razasperros.domain.repository.RazaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Estado que la pantalla dibuja. */
data class RazasUiState(
    val cargando: Boolean = true,
    val razas: List<Raza> = emptyList(),
    val hayError: Boolean = false
)

class RazasViewModel(
    private val repository: RazaRepository   // <- se inyecta (interfaz del dominio)
) : ViewModel() {

    private val _estado = MutableStateFlow(RazasUiState())
    val estado: StateFlow<RazasUiState> = _estado.asStateFlow()

    init {
        cargarRazas()
    }

    fun cargarRazas() {
        viewModelScope.launch {
            _estado.value = RazasUiState(cargando = true)
            _estado.value = try {
                RazasUiState(cargando = false, razas = repository.obtenerRazas())
            } catch (e: Exception) {
                Log.e("RazasPerros", "Error al cargar razas", e)
                RazasUiState(cargando = false, hayError = true)
            }
        }
    }

    companion object {
        /** Fábrica: toma el repositorio del contenedor de dependencias. */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RazasPerrosApp
                RazasViewModel(repository = app.container.razaRepository)
            }
        }
    }
}
