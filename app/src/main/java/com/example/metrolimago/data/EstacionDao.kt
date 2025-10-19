package com.example.metrolimago.data

import com.example.metrolimago.model.Estacion

object EstacionDao {

    // Lista ampliada basada en el mapa compartido (Línea 2)
    // Nota: nombres exactamente como en la imagen
    private val estacionesLinea2 = listOf(
        Estacion(1,  "GAMBETTA",                 "Línea 2", "Inicio en Callao."),
        Estacion(2,  "CANTA CALLAO",             "Línea 2", "Tramo Callao."),
        Estacion(3,  "BOCANEGRA",                "Línea 2", "Zona Callao."),
        Estacion(4,  "AEROPUERTO",               "Línea 2", "Conexión al Jorge Chávez."),
        Estacion(5,  "EL OLIVAR",                "Línea 2", "Callao."),
        Estacion(6,  "QUILCA",                   "Línea 2", "Callao."),
        Estacion(7,  "MORALES DUAREZ",           "Línea 2", "Límite Callao/Lima."),
        Estacion(8,  "CARMEN DE LA LEGUA",       "Línea 2", "Carmen de la Legua."),
        Estacion(9,  "SAN MARCOS",               "Línea 2", "Cerca a UNMSM."),
        Estacion(10, "LA ALBORADA",              "Línea 2", "Breña/Lima."),
        Estacion(11, "TINGO MARÍA",              "Línea 2", "Breña."),
        Estacion(12, "PARQUE MURILLO",           "Línea 2", "Breña."),
        Estacion(13, "PLAZA BOLOGNESI",          "Línea 2", "Cercado de Lima."),
        Estacion(14, "EST. CENTRAL",             "Líneas 1 y 2", "Intercambio con Línea 1."),
        Estacion(15, "CANGALLO",                 "Línea 2", "La Victoria."),
        Estacion(16, "28 DE JULIO",              "Línea 2", "La Victoria/El Agustino."),
        Estacion(17, "NICOLÁS AYLLÓN",           "Línea 2", "El Agustino."),
        Estacion(18, "CIRCUNVALACIÓN",           "Línea 2", "El Agustino."),
        Estacion(19, "SAN JUAN DE DIOS",         "Línea 2", "San Luis."),
        Estacion(20, "EVITAMIENTO",              "Línea 2", "Santa Anita."),
        Estacion(21, "ÓVALO SANTA ANITA",        "Línea 2", "Santa Anita."),
        Estacion(22, "COLECTORA INDUSTRIAL",     "Línea 2", "Santa Anita."),
        Estacion(23, "HERMILIO VALDIZÁN",        "Línea 2", "Santa Anita."),
        Estacion(24, "MERCADO SANTA ANITA",      "Línea 2", "Santa Anita."),
        Estacion(25, "JAVIER PRADO",             "Línea 2", "Ate."),
        Estacion(26, "VISTA ALEGRE",             "Línea 2", "Ate."),
        Estacion(27, "MUNICIPALIDAD DE ATE",     "Línea 2", "Terminal este.")
    )

    // Si luego agregas más líneas/ramales, añádelas aquí y concatena
    private val estaciones = estacionesLinea2

    fun getAll(): List<Estacion> = estaciones

    fun getById(id: Int): Estacion? = estaciones.find { it.id == id }
}
