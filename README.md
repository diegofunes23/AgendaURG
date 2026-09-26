# AgendaURG - Agenda Académica y de Contactos 

**AgendaURG** es una aplicación Android desarrollada en **Kotlin** con **Material Design 3**, diseñada para la gestión y consulta rápida de contactos docentes y académicos de diversas facultades.

---

##  Características y Requerimientos Cumplidos

1. **Activity Anfitriona con 2 Fragments Intercambiables**:
   - `MainActivity` funciona como contenedor anfitrión utilizando `FrameLayout`.
   - Permite alternar dinámicamente entre el listado (`ListaContactosFragment`) y el registro (`FormularioContactoFragment`).

2. **Comunicación mediante ViewModel Compartido**:
   - `ContactoViewModel` mantiene el estado reactivo (`LiveData`) compartido entre la Activity y los Fragments mediante `activityViewModels()`.

3. **Intent Explícito a DetalleActivity**:
   - Envío de datos del contacto seleccionado desde el listado hacia `DetalleActivity` utilizando `Intent` y extras (`putExtra`).

4. **Intents Implícitos (`ACTION_DIAL` y `ACTION_SEND`)**:
   - **`ACTION_DIAL`**: Abre el marcador telefónico del sistema prellenado con el número telefónico de 8 dígitos del docente.
   - **`ACTION_SEND`**: Despliega las opciones del sistema para compartir la información o enviar un mensaje/correo electrónico.

5. **Material Design 3**:
   - Uso de componentes de Material Design:
     - `MaterialToolbar` (Barra superior)
     - `MaterialCardView` (Tarjetas de contactos)
     - `FloatingActionButton` (FAB para alternar vistas)
     - `Snackbar` (Alertas e interacción en tiempo real)

---

##  Profesores Registrados en la App

1. **Ing. José Ramírez** — *Ingeniería en Sistemas* (Tel: `88112233`)
2. **Licda. Mariana González** — *Administración de Empresas* (Tel: `88223344`)
3. **Arq. Daniel Herrera** — *Arquitectura* (Tel: `88334455`)
4. **Lic. Fernando Castillo** — *Contaduría Pública* (Tel: `88445566`)
5. **Dra. Gabriela Martínez** — *Psicología* (Tel: `88556677`)

---

##  Tecnologías y Librerías

- **Lenguaje**: Kotlin
- **SDK Objetivo**: Android 14 / API 35+ (Min SDK 27)
- **Arquitectura**: MVVM (Model-View-ViewModel) + LiveData
- **UI & Layouts**: ViewBinding, Material Components (Material 3), CoordinatorLayout
- **Jetpack Components**: AppCompat, Activity KTX, Fragment KTX, ConstraintLayout

---

##  Compilación y Ejecución

Para compilar el proyecto localmente mediante Gradle:

```bash
./gradlew assembleDebug
```

---

##  Licencia

Este proyecto fue desarrollado para fines académicos e instruccionales.
