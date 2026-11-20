package com.jesus.razasperros.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesus.razasperros.R
import com.jesus.razasperros.domain.model.Raza
import com.jesus.razasperros.ui.theme.RazasPerrosTheme

// Pantalla 2: información de la raza seleccionada.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalle(raza: Raza, onRegresar: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(raza.nombre) },
                navigationIcon = {
                    IconButton(onClick = onRegresar) {
                        Icon(
                            painter = painterResource(R.drawable.ic_regresar),
                            contentDescription = stringResource(R.string.regresar)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.logo_descripcion),
                modifier = Modifier.size(120.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = raza.nombre,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            Text(
                text = valorOVacio(raza.grupo),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DatoRaza(R.string.etiqueta_grupo, valorOVacio(raza.grupo))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    DatoRaza(R.string.etiqueta_criado_para, valorOVacio(raza.criadoPara))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    DatoRaza(
                        R.string.etiqueta_temperamento,
                        valorOVacio(raza.temperamentos.joinToString(", "))
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    DatoRaza(R.string.etiqueta_esperanza_vida, valorOVacio(raza.esperanzaVida))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    DatoRaza(
                        R.string.etiqueta_peso,
                        raza.pesoKg?.takeIf { it.isNotBlank() }
                            ?.let { stringResource(R.string.formato_peso, it) }
                            ?: stringResource(R.string.sin_informacion)
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    DatoRaza(
                        R.string.etiqueta_altura,
                        raza.alturaCm?.takeIf { it.isNotBlank() }
                            ?.let { stringResource(R.string.formato_altura, it) }
                            ?: stringResource(R.string.sin_informacion)
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                    DatoRaza(R.string.etiqueta_origen, valorOVacio(raza.origen))
                }
            }
        }
    }
}

@Composable
private fun DatoRaza(etiqueta: Int, valor: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(etiqueta),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = valor,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun valorOVacio(valor: String?): String =
    valor?.takeIf { it.isNotBlank() } ?: stringResource(R.string.sin_informacion)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PantallaDetallePreview() {
    RazasPerrosTheme {
        PantallaDetalle(raza = razaEjemplo, onRegresar = {})
    }
}
