package com.example.fragmentjsonbbdd.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentjsonbbdd.R
import com.example.fragmentjsonbbdd.database.DataRepository
import com.example.fragmentjsonbbdd.model.alumno

class FragmentFichaAlumno : Fragment(){
    var textViewNombre: TextView? = null
    var textViewApellido: TextView? = null
    var thiscontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_ficha_alumnos, container, false)
        val idAlumno = arguments?.getString("idAlumno")?.toInt()

        thiscontext = container?.getContext();
        var dataRepository = DataRepository(thiscontext!!)

        var alumnosGuardados = idAlumno?.let { dataRepository.GetAlumnoOne(it.toInt()) }

        textViewNombre = v.findViewById<View>(R.id.textViewFichaNombre) as TextView
        textViewApellido = v.findViewById<View>(R.id.textViewFichaApellido) as TextView

        if (alumnosGuardados != null) {
            textViewNombre!!.text = alumnosGuardados.get(0).nombre
            textViewApellido!!.text = alumnosGuardados.get(0).apellido
        }



        return v
    }
    fun updateData(item: alumno?) {
        if (item!=null) {
            textViewNombre!!.text = item.nombre
            textViewApellido!!.text = item.apellido
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFichaAlumno().apply {
            }
    }
}