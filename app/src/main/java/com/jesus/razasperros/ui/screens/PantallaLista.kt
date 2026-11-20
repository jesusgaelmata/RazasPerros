package com.jesus.razasperros.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesus.razasperros.R
import com.jesus.razasperros.domain.model.Raza
import com.jesus.razasperros.ui.theme.RazasPerrosTheme

// Pantalla 1: lista de razas (vienen de la base de datos a través del ViewModel).
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLista(
    razas: List<Raza>?,
    hayError: Boolean,
    onRazaClick: (Raza) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.titulo_lista)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        when {
            hayError -> Box(
                modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.error_carga),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }

            razas == null -> Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.cargando),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            else -> LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { EncabezadoLista() }
                items(razas, key = { it.id }) { raza ->
                    TarjetaRaza(raza = raza, onClick = { onRazaClick(raza) })
                }
            }
        }
    }
}

@Composable
private fun EncabezadoLista() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = stringResource(R.string.logo_descripcion),
            modifier = Modifier.size(96.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.subtitulo_lista),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun TarjetaRaza(raza: Raza, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = raza.nombre.take(1),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = raza.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = raza.grupo?.takeIf { it.isNotBlank() }
                        ?: stringResource(R.string.sin_informacion),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

// Datos de ejemplo solo para la vista previa de Android Studio
internal val razaEjemplo = Raza(
    id = 1,
    nombre = "Labrador Retriever",
    grupo = "Sporting",
    criadoPara = "Water retrieving",
    esperanzaVida = "10 - 13 years",
    temperamentos = listOf("Kind", "Outgoing", "Agile", "Gentle", "Intelligent"),
    origen = "Canada, United Kingdom",
    pesoKg = "25 - 36",
    alturaCm = "55 - 62"
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PantallaListaPreview() {
    RazasPerrosTheme {
        PantallaLista(
            razas = listOf(
                razaEjemplo,
                razaEjemplo.copy(id = 2, nombre = "Beagle", grupo = "Hound"),
                razaEjemplo.copy(id = 3, nombre = "Chihuahua", grupo = "Toy"),
                razaEjemplo.copy(id = 4, nombre = "German Shepherd", grupo = "Herding")
            ),
            hayError = false,
            onRazaClick = {}
        )
    }
}
