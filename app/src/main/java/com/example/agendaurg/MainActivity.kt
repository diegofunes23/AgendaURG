package com.example.agendaurg

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.agendaurg.databinding.ActivityMainBinding
import com.example.agendaurg.fragment.FormularioContactoFragment
import com.example.agendaurg.fragment.ListaContactosFragment
import com.example.agendaurg.viewmodel.ContactoViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val contactoViewModel: ContactoViewModel by viewModels()

    private var mostrandoFormulario = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            cambiarFragment(ListaContactosFragment())
        }

        binding.fabSwitch.setOnClickListener {
            if (mostrandoFormulario) {
                mostrandoFormulario = false
                binding.fabSwitch.setImageResource(android.R.drawable.ic_input_add)
                cambiarFragment(ListaContactosFragment())
                Snackbar.make(binding.mainLayout, "Lista de Contactos", Snackbar.LENGTH_SHORT).show()
            } else {
                mostrandoFormulario = true
                binding.fabSwitch.setImageResource(android.R.drawable.ic_menu_revert)
                cambiarFragment(FormularioContactoFragment())
                Snackbar.make(binding.mainLayout, "Formulario de Registro", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun cambiarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
