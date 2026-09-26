package com.example.agendaurg.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaurg.databinding.ItemContactoBinding
import com.example.agendaurg.model.Contacto

class ContactoAdapter(
    private var contactos: List<Contacto> = emptyList(),
    private val onContactoClick: (Contacto) -> Unit,
    private val onVerDetalleClick: (Contacto) -> Unit
) : RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>() {

    class ContactoViewHolder(val binding: ItemContactoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val binding = ItemContactoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto = contactos[position]
        with(holder.binding) {
            tvNombre.text = contacto.nombre
            tvTelefono.text = "Tel: ${contacto.telefono}"
            tvCategoria.text = contacto.categoria

            root.setOnClickListener {
                onContactoClick(contacto)
            }

            btnVerDetalle.setOnClickListener {
                onVerDetalleClick(contacto)
            }
        }
    }

    override fun getItemCount(): Int = contactos.size

    fun updateList(newList: List<Contacto>) {
        contactos = newList
        notifyDataSetChanged()
    }
}
