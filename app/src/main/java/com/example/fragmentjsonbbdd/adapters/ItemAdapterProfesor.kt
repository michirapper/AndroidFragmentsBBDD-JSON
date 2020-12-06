package com.example.fragmentjsonbbdd.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentjsonbbdd.R
import com.example.fragmentjsonbbdd.model.profesor

class ItemAdapterProfesor(var items: ArrayList<profesor>, private val listener: (profesor) -> Unit) : RecyclerView.Adapter<ItemAdapterProfesor.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterProfesor.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lista_de_items, parent, false)
        val viewHolder = ViewHolder(v)
        return viewHolder
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ItemAdapterProfesor.ViewHolder, position: Int) {
        holder.bindItems(items[position])
        holder.itemView.setOnClickListener { listener(items[position]) }
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return items.size
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(cliente: profesor) {
            val textViewNombre = itemView.findViewById<TextView>(R.id.textViewNombre)
            textViewNombre.text = cliente.nombre
        }
    }
}