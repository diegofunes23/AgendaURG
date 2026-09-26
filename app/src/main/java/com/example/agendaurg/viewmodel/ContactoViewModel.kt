package com.example.agendaurg.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.agendaurg.model.Contacto

class ContactoViewModel : ViewModel() {

    private val _contactos = MutableLiveData<List<Contacto>>(
        listOf(
            Contacto(
                id = "1",
                nombre = "Ing. José Ramírez",
                telefono = "88112233",
                email = "jramirez@universidad.edu",
                categoria = "Ingeniería en Sistemas",
                descripcion = "Profesor Titular de la Facultad de Ingeniería en Sistemas. Cubículo 101."
            ),
            Contacto(
                id = "2",
                nombre = "Licda. Mariana González",
                telefono = "88223344",
                email = "mgonzalez@universidad.edu",
                categoria = "Administración de Empresas",
                descripcion = "Coordinadora de la Licenciatura en Administración de Empresas. Edificio B."
            ),
            Contacto(
                id = "3",
                nombre = "Arq. Daniel Herrera",
                telefono = "88334455",
                email = "dherrera@universidad.edu",
                categoria = "Arquitectura",
                descripcion = "Docente en la Facultad de Arquitectura y Diseño Urbanístico. Taller 4."
            ),
            Contacto(
                id = "4",
                nombre = "Lic. Fernando Castillo",
                telefono = "88445566",
                email = "fcastillo@universidad.edu",
                categoria = "Contaduría Pública",
                descripcion = "Profesor de Finanzas y Contaduría Pública. Cubículo 204."
            ),
            Contacto(
                id = "5",
                nombre = "Dra. Gabriela Martínez",
                telefono = "88556677",
                email = "gmartinez@universidad.edu",
                categoria = "Psicología",
                descripcion = "Docente e Investigadora en la Facultad de Psicología. Edificio C."
            )
        )
    )
    val contactos: LiveData<List<Contacto>> = _contactos

    private val _selectedContacto = MutableLiveData<Contacto?>()
    val selectedContacto: LiveData<Contacto?> = _selectedContacto

    fun seleccionarContacto(contacto: Contacto) {
        _selectedContacto.value = contacto
    }

    fun agregarContacto(nuevoContacto: Contacto) {
        val listaActual = _contactos.value?.toMutableList() ?: mutableListOf()
        listaActual.add(0, nuevoContacto)
        _contactos.value = listaActual
        _selectedContacto.value = nuevoContacto
    }
}
