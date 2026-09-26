package com.example.agendaurg.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.agendaurg.databinding.FragmentFormularioContactoBinding
import com.example.agendaurg.model.Contacto
import com.example.agendaurg.viewmodel.ContactoViewModel
import com.google.android.material.snackbar.Snackbar

class FormularioContactoFragment : Fragment() {

    private var _binding: FragmentFormularioContactoBinding? = null
    private val binding get() = _binding!!

    private val contactoViewModel: ContactoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormularioContactoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contactoViewModel.selectedContacto.observe(viewLifecycleOwner) { seleccionado ->
            if (seleccionado != null) {
                binding.tvEstadoViewModel.text = "Seleccionado en ViewModel: ${seleccionado.nombre}"
            } else {
                binding.tvEstadoViewModel.text = "Ningún contacto seleccionado"
            }
        }

        binding.btnGuardar.setOnClickListener {
            guardarContacto()
        }
    }

    private fun guardarContacto() {
        val nombre = binding.etNombre.text.toString().trim()
        val telefono = binding.etTelefono.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val categoria = binding.etCategoria.text.toString().trim()
        val descripcion = binding.etDescripcion.text.toString().trim()

        if (nombre.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor ingresa nombre y teléfono", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoContacto = Contacto(
            nombre = nombre,
            telefono = telefono,
            email = email,
            categoria = if (categoria.isNotBlank()) categoria else "Emergencia",
            descripcion = descripcion
        )

        contactoViewModel.agregarContacto(nuevoContacto)

        Snackbar.make(binding.root, "Contacto guardado correctamente", Snackbar.LENGTH_SHORT).show()

        binding.etNombre.text?.clear()
        binding.etTelefono.text?.clear()
        binding.etEmail.text?.clear()
        binding.etCategoria.text?.clear()
        binding.etDescripcion.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
