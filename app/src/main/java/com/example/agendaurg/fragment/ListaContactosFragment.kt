package com.example.agendaurg.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendaurg.DetalleActivity
import com.example.agendaurg.R
import com.example.agendaurg.adapter.ContactoAdapter
import com.example.agendaurg.databinding.FragmentListaContactosBinding
import com.example.agendaurg.model.Contacto
import com.example.agendaurg.viewmodel.ContactoViewModel
import com.google.android.material.snackbar.Snackbar

class ListaContactosFragment : Fragment() {

    private var _binding: FragmentListaContactosBinding? = null
    private val binding get() = _binding!!

    private val contactoViewModel: ContactoViewModel by activityViewModels()
    private lateinit var adapter: ContactoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaContactosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = ContactoAdapter(
            onContactoClick = { contacto ->
                contactoViewModel.seleccionarContacto(contacto)
                Snackbar.make(binding.root, "Seleccionado: ${contacto.nombre}", Snackbar.LENGTH_SHORT).show()
            },
            onVerDetalleClick = { contacto ->
                contactoViewModel.seleccionarContacto(contacto)
                abrirDetalle(contacto)
            }
        )

        binding.rvContactos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvContactos.adapter = adapter
    }

    private fun observeViewModel() {
        contactoViewModel.contactos.observe(viewLifecycleOwner) { contactos ->
            adapter.updateList(contactos)
        }

        contactoViewModel.selectedContacto.observe(viewLifecycleOwner) { seleccionado ->
            if (seleccionado != null) {
                binding.tvSeleccionado.text = "${seleccionado.nombre} - ${seleccionado.telefono}"
            } else {
                binding.tvSeleccionado.text = "Ninguno seleccionado"
            }
        }
    }

    private fun abrirDetalle(contacto: Contacto) {
        val intent = Intent(requireContext(), DetalleActivity::class.java).apply {
            putExtra(getString(R.string.extra_nombre), contacto.nombre)
            putExtra(getString(R.string.extra_telefono), contacto.telefono)
            putExtra(getString(R.string.extra_email), contacto.email)
            putExtra(getString(R.string.extra_categoria), contacto.categoria)
            putExtra(getString(R.string.extra_descripcion), contacto.descripcion)
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
