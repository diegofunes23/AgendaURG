package com.example.agendaurg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agendaurg.databinding.ActivityDetalleBinding
import com.google.android.material.snackbar.Snackbar

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding

    private var nombre: String? = null
    private var telefono: String? = null
    private var email: String? = null
    private var categoria: String? = null
    private var descripcion: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.detalleLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.toolbarDetalle.setNavigationOnClickListener {
            finish()
        }

        obtenerDatosIntent()
        setupBotonesIntentsImplicitos()
    }

    private fun obtenerDatosIntent() {
        nombre = intent.getStringExtra(getString(R.string.extra_nombre))
        telefono = intent.getStringExtra(getString(R.string.extra_telefono))
        email = intent.getStringExtra(getString(R.string.extra_email))
        categoria = intent.getStringExtra(getString(R.string.extra_categoria))
        descripcion = intent.getStringExtra(getString(R.string.extra_descripcion))

        binding.tvNombre.text = nombre ?: "Sin nombre"
        binding.tvTelefono.text = if (!telefono.isNullOrBlank()) "Tel: $telefono" else "Sin teléfono"
        binding.tvEmail.text = if (!email.isNullOrBlank()) "Email: $email" else "Sin correo"
        binding.tvCategoria.text = if (!categoria.isNullOrBlank()) "Categoría: $categoria" else "Emergencia"
        binding.tvDescripcion.text = if (!descripcion.isNullOrBlank()) "Notas: $descripcion" else "Sin notas adicionales."
    }

    private fun setupBotonesIntentsImplicitos() {
        // Intent Implícito 1: ACTION_DIAL
        binding.btnLlamar.setOnClickListener {
            if (!telefono.isNullOrEmpty()) {
                Snackbar.make(binding.detalleLayout, "Abriendo marcador...", Snackbar.LENGTH_SHORT).show()
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$telefono")
                }
                startActivity(dialIntent)
            } else {
                Snackbar.make(binding.detalleLayout, "Sin número de teléfono", Snackbar.LENGTH_SHORT).show()
            }
        }

        // Intent Implícito 2: ACTION_SEND
        binding.btnEnviar.setOnClickListener {
            val mensajeText = "Contacto: $nombre\nTeléfono: $telefono\nCategoría: $categoria\nNotas: $descripcion"
            Snackbar.make(binding.detalleLayout, "Compartiendo contacto...", Snackbar.LENGTH_SHORT).show()

            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Contacto de Urgencia - $nombre")
                putExtra(Intent.EXTRA_TEXT, mensajeText)
            }
            startActivity(Intent.createChooser(sendIntent, "Enviar contacto por:"))
        }
    }
}
