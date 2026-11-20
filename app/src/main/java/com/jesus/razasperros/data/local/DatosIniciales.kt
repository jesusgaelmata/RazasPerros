package com.jesus.razasperros.data.local

import com.jesus.razasperros.data.remote.MedidaDto
import com.jesus.razasperros.data.remote.RazaDto

/**
 * Datos iniciales que vienen dentro de la app.
 * Se guardan en la base de datos la primera vez si TheDogAPI no responde
 * (por ejemplo, sin internet o sin API key), para que la app siempre funcione.
 */
object DatosIniciales {

    private fun raza(
        id: Int, nombre: String, criadoPara: String?, grupo: String?, vida: String,
        temperamento: String, origen: String?,
        pesoLb: String, pesoKg: String, alturaIn: String, alturaCm: String
    ) = RazaDto(
        id = id,
        name = nombre,
        bredFor = criadoPara,
        breedGroup = grupo,
        lifeSpan = vida,
        temperament = temperamento,
        origin = origen,
        weight = MedidaDto(imperial = pesoLb, metric = pesoKg),
        height = MedidaDto(imperial = alturaIn, metric = alturaCm),
        referenceImageId = null
    )

    val razas: List<RazaDto> = listOf(
        raza(1, "Labrador Retriever", "Water retrieving", "Sporting", "10 - 13 years",
            "Kind, Outgoing, Agile, Gentle, Intelligent, Trusting, Even Tempered",
            "Canada, United Kingdom", "55 - 80", "25 - 36", "21.5 - 24.5", "55 - 62"),
        raza(2, "Golden Retriever", "Retrieving", "Sporting", "10 - 12 years",
            "Intelligent, Kind, Reliable, Friendly, Trustworthy, Confident",
            "Scotland", "55 - 75", "25 - 34", "21.5 - 24", "55 - 61"),
        raza(3, "Cocker Spaniel", "Flushing and retrieving birds", "Sporting", "10 - 14 years",
            "Trainable, Friendly, Affectionate, Playful, Quiet, Faithful",
            "United States", "20 - 30", "9 - 14", "13.5 - 15.5", "34 - 39"),
        raza(4, "Beagle", "Rabbit, hare hunting", "Hound", "13 - 16 years",
            "Amiable, Even Tempered, Excitable, Determined, Gentle, Intelligent",
            "United Kingdom", "20 - 35", "9 - 16", "13 - 15", "33 - 38"),
        raza(5, "Basset Hound", "Hunting by scent", "Hound", "12 - 13 years",
            "Tenacious, Friendly, Affectionate, Devoted, Sweet-Tempered, Gentle",
            "France", "40 - 65", "18 - 29", "14", "36"),
        raza(6, "Dachshund", "Trailing, hunting badgers", "Hound", "12 - 15 years",
            "Stubborn, Lively, Playful, Clever, Courageous, Devoted",
            "Germany", "16 - 32", "7 - 15", "8 - 9", "20 - 23"),
        raza(7, "Chihuahua", "Companionship", "Toy", "12 - 18 years",
            "Devoted, Lively, Alert, Courageous, Quick",
            "Mexico", "2 - 6", "1 - 3", "5 - 8", "13 - 20"),
        raza(8, "Pug", "Lapdog", "Toy", "12 - 15 years",
            "Docile, Clever, Charming, Stubborn, Sociable, Playful, Quiet, Attentive",
            "China", "14 - 18", "6 - 8", "10 - 14", "25 - 36"),
        raza(9, "Pomeranian", "Companion", "Toy", "12 - 16 years",
            "Extroverted, Friendly, Sociable, Playful, Active, Intelligent",
            "Germany, Poland", "3 - 7", "1 - 3", "8 - 11", "20 - 28"),
        raza(10, "Shih Tzu", "Companion", "Toy", "10 - 18 years",
            "Clever, Spunky, Outgoing, Friendly, Affectionate, Lively, Alert, Loyal",
            "China", "9 - 16", "4 - 7", "9 - 10.5", "23 - 27"),
        raza(11, "German Shepherd", "Herding, guarding sheep", "Herding", "10 - 13 years",
            "Alert, Loyal, Obedient, Curious, Confident, Courageous",
            "Germany", "50 - 90", "23 - 41", "22 - 26", "56 - 66"),
        raza(12, "Border Collie", "Sheep herder", "Herding", "12 - 16 years",
            "Tenacious, Keen, Energetic, Responsive, Alert, Intelligent",
            "Scotland, England", "30 - 55", "14 - 25", "19 - 22", "48 - 56"),
        raza(13, "Siberian Husky", "Sled pulling", "Working", "12 - 15 years",
            "Outgoing, Friendly, Alert, Gentle, Intelligent",
            "Russia", "35 - 60", "16 - 27", "20 - 23.5", "51 - 60"),
        raza(14, "Boxer", "Bull baiting, guardian", "Working", "8 - 10 years",
            "Devoted, Fearless, Friendly, Cheerful, Energetic, Loyal, Playful, Confident",
            "Germany", "50 - 70", "23 - 32", "21.5 - 25", "55 - 64"),
        raza(15, "Rottweiler", "Cattle drover, guardian", "Working", "8 - 10 years",
            "Steady, Good-natured, Fearless, Devoted, Alert, Obedient, Confident",
            "Germany", "75 - 135", "34 - 61", "22 - 27", "56 - 69"),
        raza(16, "Doberman Pinscher", "Guarding", "Working", "10 years",
            "Fearless, Energetic, Alert, Loyal, Confident, Obedient",
            "Germany", "60 - 80", "27 - 36", "24 - 28", "61 - 71"),
        raza(17, "Jack Russell Terrier", "Fox hunting", "Terrier", "12 - 14 years",
            "Athletic, Lively, Energetic, Clever, Fearless, Intelligent",
            "United Kingdom", "13 - 17", "6 - 8", "12 - 15", "30 - 38"),
        raza(18, "Yorkshire Terrier", "Ratting, companion", "Toy", "12 - 16 years",
            "Bold, Independent, Confident, Intelligent, Courageous",
            "United Kingdom", "4 - 7", "2 - 3", "8 - 9", "20 - 23"),
        raza(19, "Bulldog", "Bull baiting", "Non-Sporting", "8 - 10 years",
            "Docile, Willful, Friendly, Gregarious",
            "United Kingdom", "40 - 50", "18 - 23", "14 - 15", "36 - 38"),
        raza(20, "Poodle (Standard)", "Water retrieving", "Non-Sporting", "10 - 18 years",
            "Intelligent, Active, Alert, Faithful, Trainable, Instinctual",
            "Germany, France", "45 - 70", "20 - 32", "15+", "38+")
    )
}
