package com.jesus.razasperros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jesus.razasperros.domain.model.Raza
import com.jesus.razasperros.ui.screens.PantallaDetalle
import com.jesus.razasperros.ui.screens.PantallaInicio
import com.jesus.razasperros.ui.screens.PantallaLista
import com.jesus.razasperros.ui.theme.RazasPerrosTheme
import com.jesus.razasperros.ui.viewmodel.RazasViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RazasPerrosTheme {
                AppRazas()
            }
        }
    }
}

@Composable
fun AppRazas(
    // La vista pide el ViewModel; la fábrica le inyecta el repositorio
    viewModel: RazasViewModel = viewModel(factory = RazasViewModel.Factory)
) {
    // La vista observa el estado: cada cambio vuelve a dibujar la pantalla
    val estado by viewModel.estado.collectAsState()

    var razaSeleccionada by remember { mutableStateOf<Raza?>(null) }
    var mostrarInicio by remember { mutableStateOf(true) }

    val seleccionada = razaSeleccionada
    if (mostrarInicio) {
        // Pantalla de inicio
        PantallaInicio(onVerRazas = { mostrarInicio = false })
    } else if (seleccionada == null) {
        // Pantalla 1: lista (datos leídos de la base de datos vía ViewModel)
        BackHandler { mostrarInicio = true }
        PantallaLista(
            razas = if (estado.cargando) null else estado.razas,
            hayError = estado.hayError,
            onRazaClick = { razaSeleccionada = it }
        )
    } else {
        // Pantalla 2: detalle
        BackHandler { razaSeleccionada = null }
        PantallaDetalle(
            raza = seleccionada,
            onRegresar = { razaSeleccionada = null }
        )
    }
}
