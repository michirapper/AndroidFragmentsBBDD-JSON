package com.example.fragmentjsonbbdd.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentjsonbbdd.R
import com.example.fragmentjsonbbdd.adapters.ItemAdapterProfesor
import com.example.fragmentjsonbbdd.database.DataRepository
import com.example.fragmentjsonbbdd.model.profesor

class FragmentListaProfesor : Fragment(){
    //var activityListener: View.OnClickListener? = null
    var itemSeleccionado: profesor? = null
    var thiscontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_lista_profesor, container, false)

        val asignatura = arguments!!.getString("asignatura")

        val recyclerViewLista: RecyclerView = v.findViewById<View>(R.id.recyclerviewlista) as RecyclerView
        thiscontext = container?.getContext();
        var dataRepository = DataRepository(thiscontext!!)
        var numeroAsignatura: Int
        if (asignatura.equals("BBDD")){
            numeroAsignatura = 2
        }else{
            numeroAsignatura = 1
        }


        var profesoresGuardados = dataRepository.getProfesorOne(numeroAsignatura)
        var profesor = profesoresGuardados.get(0).profesores

        var items = ArrayList<profesor>()

        for (i in 0..profesor.size-1) {
            items.add(profesor(profesor.get(i).nombre.toString(), profesor.get(i).apellido.toString()))
        }

        val adapter = ItemAdapterProfesor(items) { item ->
            itemSeleccionado = item

        }

        recyclerViewLista.setAdapter(adapter)
        recyclerViewLista.setLayoutManager(LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false))

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentListaProfesor().apply {}
    }
}