package com.example.fragmentjsonbbdd.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentjsonbbdd.R
import com.example.fragmentjsonbbdd.adapters.ItemAdapterProfesor
import com.example.fragmentjsonbbdd.model.profesor

class FragmentListaProfesor : Fragment(){
    var activityListener: View.OnClickListener? = null
    var itemSeleccionado: profesor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_lista_profesor, container, false)

        val recyclerViewLista: RecyclerView = v.findViewById<View>(R.id.recyclerviewlista) as RecyclerView

        var items = ArrayList<profesor>()
        for (i in 1..20){
            items.add(profesor(i.toString(), i.toString()))
        }

        val adapter = ItemAdapterProfesor(items) { item ->
            itemSeleccionado = item
            if (activityListener != null) {
                activityListener!!.onClick(view)
            }
        }

        recyclerViewLista.setAdapter(adapter)
        recyclerViewLista.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))



        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentListaProfesor().apply {}
    }
}