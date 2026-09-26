package com.example.agendaurg.model

import java.io.Serializable

data class Contacto(
    val id: String = System.currentTimeMillis().toString(),
    val nombre: String,
    val telefono: String,
    val email: String = "",
    val categoria: String = "Emergencia",
    val descripcion: String = ""
) : Serializable
