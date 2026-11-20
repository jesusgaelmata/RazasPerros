# Razas de Perros 🐶

Aplicación Android desarrollada en **Kotlin** con **Jetpack Compose** que muestra un catálogo de razas de perros.
Las razas se descargan de [TheDogAPI](https://thedogapi.com/) con **Retrofit**, se guardan en una base de datos local
**SQLite** administrada con **Room** y se muestran en pantalla a través de un **ViewModel** (arquitectura MVVM por capas).
Si no hay internet, la app sigue funcionando con los datos guardados.

**Autor:** Jesús Gael Mata Jiménez · Universidad Tecnológica de Tula-Tepeji · 4DSM-G2

## Funcionalidades (primera etapa)

- Pantalla de inicio con el logo y el botón **Ver razas**.
- Lista de razas ordenadas por nombre, con indicador de carga y mensaje de error.
- Detalle de cada raza: grupo, para qué fue criada, temperamento, esperanza de vida, peso, altura y origen.
- Funcionamiento sin conexión (caché en SQLite + datos iniciales incluidos en la app).

## Arquitectura

```
Vista (Compose) → ViewModel → RazaRepository (dominio) → RazaRepositoryImpl → DAO → Room / SQLite
                                                                         ↘ Retrofit → TheDogAPI
```

| Paquete | Contenido |
|---|---|
| `ui/screens` | `PantallaInicio`, `PantallaLista`, `PantallaDetalle` |
| `ui/viewmodel` | `RazasViewModel` y `RazasUiState` (StateFlow) |
| `ui/theme` | Tema Material 3 (colores, tipografía) |
| `domain/model` | Modelo de dominio `Raza` |
| `domain/repository` | Interfaz `RazaRepository` |
| `data/remote` | `DogApiService`, `RetrofitClient`, `RazaDto` |
| `data/local` | Entidades Room, `RazaDao`, `RazasDatabase`, `DatosIniciales` |
| `data/mapper` | Conversión DTO → Entity → Dominio |
| `data/repository` | `RazaRepositoryImpl` |
| `di` | `AppContainer` (inyección de dependencias manual) |

El modelo de datos está documentado en [`docs/modelo_relacional.md`](docs/modelo_relacional.md).

## Tecnologías

Kotlin 2.2 · Jetpack Compose (BOM 2026.02) · Material 3 · Lifecycle ViewModel 2.11 · Retrofit 2.11 + Gson ·
Room 2.8 (KSP) · minSdk 24 / targetSdk 37.

## Cómo ejecutar

1. Clonar el repositorio: `git clone https://github.com/jesusgaelmata/RazasPerros.git`
2. Abrir la carpeta en Android Studio y esperar la sincronización de Gradle.
3. Ejecutar en un emulador o dispositivo con Android 7.0 (API 24) o superior.
