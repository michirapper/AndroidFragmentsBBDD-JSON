package com.example.fragmentjsonbbdd

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentjsonbbdd.database.*
import com.example.fragmentjsonbbdd.fragments.FragmentListaProfesor

class MainActivity : AppCompatActivity() {

    var frameLayoutFragmentProfesor: FrameLayout? = null
    var frameLayoutLista: FrameLayout? = null
  //  var frameLayoutFicha: FrameLayout? = null

    var listaFragment: FragmentListaProfesor? = null
  //  var fichaFragment: FichaFragment? = null
    var segundoFragmentActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rellenarAsignaturas()

        var dataRepository = DataRepository(this)

        //Rellenamos el array con al array que hemos recibido de la Query
        val spinner = findViewById<Spinner>(R.id.spinner)
        var pedidosGuardados = dataRepository.getAsignaturas()
        var ArraySpinner = ArrayList<String>()
        ArraySpinner.add("")
        for (items in pedidosGuardados) {
            ArraySpinner.add(items.nombre.toString())
        }
        //Rellenamos el spinner con el array que acabamos de hacer
        spinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ArraySpinner)
        if (spinner != null) {

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

                    Toast.makeText(
                        this@MainActivity,
                        spinner.selectedItem.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    verProfesores(spinner.selectedItem.toString())
                   
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }





    }

    private fun rellenarAsignaturas() {
        var asignaturaBBDD = Asignaturas(1, "BBDD")
        var asignaturaProg = Asignaturas(2, "Programacion")

        var alumno1 = Alumnos(1, "Alumno1", "ApellidoAlumno1")
        var alumno2 = Alumnos(2, "Alumno2", "ApellidoAlumno2")
        var alumno3 = Alumnos(3, "Alumno3", "ApellidoAlumno3")

        var profesores1 = Profesores(1, "Antonio", "Mendoza")
        var profesores2 = Profesores(2, "Manuel", "Perez")



        var listaAlumnosBBDD = ArrayList<Alumnos>()
        listaAlumnosBBDD.add(alumno1)
        listaAlumnosBBDD.add(alumno2)

        var listaAlumnosProg = ArrayList<Alumnos>()
        listaAlumnosProg.add(alumno3)

        var listaProfesoresProg = ArrayList<Profesores>()
        listaProfesoresProg.add(profesores1)

        var listaProfesoresBBDD= ArrayList<Profesores>()
        listaProfesoresBBDD.add(profesores2)

        var asignaturasAlumnos1 = AsignaturasAlumnos(asignaturaBBDD, listaAlumnosBBDD)
        var asignaturasAlumnos2 = AsignaturasAlumnos(asignaturaProg, listaAlumnosProg)

        var asignaturasProfesores2 = AsignaturasProfesores(asignaturaProg, listaProfesoresProg)
        var asignaturasProfesores1 = AsignaturasProfesores(asignaturaBBDD, listaProfesoresBBDD)



        var dataRepository = DataRepository(this)


        dataRepository.insertAsignaturasAlumnos(asignaturasAlumnos1)
        dataRepository.insertAsignaturasAlumnos(asignaturasAlumnos2)

        dataRepository.insertAsignaturasProfesores(asignaturasProfesores1)
        dataRepository.insertAsignaturasProfesores(asignaturasProfesores2)

    }
    //    var activityListener = View.OnClickListener {
//        if (frameLayoutFragment!=null) {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.frameLayoutFragment, fichaFragment!!)
//            fragmentTransaction.commit()
//            fragmentManager.executePendingTransactions()
//            segundoFragmentActivo = true
//        }
//
//
//        fichaFragment!!.updateData(listaFragment!!.itemSeleccionado)
//    }


    private fun verProfesores(asignatura: String){
        if (!asignatura.equals("")) {
            frameLayoutFragmentProfesor = findViewById(R.id.frameLayoutProfesor)
            //frameLayoutFicha = findViewById(R.id.frameLayoutFicha)
            frameLayoutFragmentProfesor = findViewById(R.id.frameLayoutProfesor)



            listaFragment = FragmentListaProfesor.newInstance()
            // listaFragment!!.activityListener = activityListener

            //fichaFragment = FichaFragment()

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

//            val args = Bundle()
//            args.putInt("position", 1)
//            listaFragment!!.setArguments(args)

        if (frameLayoutFragmentProfesor ==null){
            // HORIZONTAL
            fragmentTransaction.add(R.id.frameLayoutProfesor, listaFragment!!)
           // fragmentTransaction.add(R.id.frameLayoutFicha, fichaFragment!!)
        }
        else {
            fragmentTransaction.add(R.id.frameLayoutProfesor, listaFragment!!)
        }

            fragmentTransaction.commit()
        }
    }



    override fun onBackPressed() {
        if (segundoFragmentActivo && frameLayoutFragmentProfesor != null){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayoutProfesor, listaFragment!!)
            fragmentTransaction.commit()
            fragmentManager.executePendingTransactions()
            segundoFragmentActivo = false
        }
        else{
            super.onBackPressed()
        }
    }


}