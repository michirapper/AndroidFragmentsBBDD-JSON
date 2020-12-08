package com.example.fragmentjsonbbdd

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentjsonbbdd.activities.alumnoFichaActivity
import com.example.fragmentjsonbbdd.database.*
import com.example.fragmentjsonbbdd.fragments.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {

    var frameLayoutFragmentProfesor: FrameLayout? = null
    var frameLayoutFragmentAlumnos: FrameLayout? = null
    var frameLayoutFragmentFicha: FrameLayout? = null
    var frameLayoutLista: FrameLayout? = null
  //  var frameLayoutFicha: FrameLayout? = null

    var listaFragmentProfesor: FragmentListaProfesor? = null

    var listaFragmentAlumno: FragmentListaAlumno? = null

    var fichaFragment: FragmentFichaAlumno? = null

    var segundoFragmentActivo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rellenarAsignaturas()
        creacionActivity()


    }

    private fun rellenarAsignaturas() {

        var dataRepository = DataRepository(this)

        var bufferedReaderRecurso = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.datos)))
        var textoLeido = bufferedReaderRecurso.readLine()
        val todo = StringBuilder()
        while (textoLeido != null) {
            todo.append(textoLeido + "\n")
            textoLeido = bufferedReaderRecurso.readLine()
        }
        textoLeido = todo.toString()
        bufferedReaderRecurso.close()

        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("asignaturas")
        var listaAlumnos = ArrayList<Alumnos>()
        var listaProfesores = ArrayList<Profesores>()

        for (i in 0 until jsonArray.length()) {

            listaAlumnos.clear()
            listaProfesores.clear()

            var asignatura = Asignaturas(i + 1, jsonArray.get(i).toString())

            val asignaturaTex = jsonArray.get(i).toString()

            val jsonArrayProf = jsonObject.optJSONArray("profesores")

            for (i in 0 until jsonArrayProf.length()) {

                val objeto = jsonArrayProf.getJSONObject(i)

                if (objeto.optString("asignatura").equals(asignaturaTex)) {

                    var codigoProfesor = objeto.optString("codigo")
                    var nombreProfesor = objeto.optString("nombre")
                    var apellidoProfesor = objeto.optString("apellido")
                    var profesor = Profesores(codigoProfesor.toString().toInt(), nombreProfesor.toString(), apellidoProfesor.toString())
                    listaProfesores.add(profesor)
                }

            }
            val jsonArrayAlumno = jsonObject.optJSONArray("alumnos")

            for (i in 0 until jsonArrayAlumno.length()) {

                val objeto = jsonArrayAlumno.getJSONObject(i)

                val jsonArrayAsignaturas = objeto.optJSONArray("Asignaturas")

                for (i in 0 until jsonArrayAsignaturas.length()) {

                    val objetoAsignatura = jsonArrayAsignaturas[i].toString()

                    if (objetoAsignatura.equals(asignaturaTex)) {


                        var codigoAlumno = objeto.optString("codigo")
                        var nombreAlumno = objeto.optString("nombre")
                        var apellidoAlumno = objeto.optString("apellido")

                        //nombre.setText(nombreAlumno.toString())

                        var alumno = Alumnos(codigoAlumno.toString().toInt(), nombreAlumno.toString(), apellidoAlumno.toString())
                        listaAlumnos.add(alumno)
                    }
                }
            }


            var asignaturasAlumnos = AsignaturasAlumnos(asignatura, listaAlumnos)
            var asignaturasProfesores = AsignaturasProfesores(asignatura, listaProfesores)

            dataRepository.insertAsignaturasAlumnos(asignaturasAlumnos)
            dataRepository.insertAsignaturasProfesores(asignaturasProfesores)

        }




//        var asignaturaBBDD = Asignaturas(1, "BBDD")
//        var asignaturaProg = Asignaturas(2, "Programacion")
//
//        var alumno1 = Alumnos(1, "Alumno1", "ApellidoAlumno1")
//        var alumno2 = Alumnos(2, "Alumno2", "ApellidoAlumno2")
//        var alumno3 = Alumnos(3, "Alumno3", "ApellidoAlumno3")
//
//        var profesores1 = Profesores(1, "Antonio", "Mendoza")
//        var profesores2 = Profesores(2, "Manuel", "Perez")
//
//
//
//        var listaAlumnosBBDD = ArrayList<Alumnos>()
//        listaAlumnosBBDD.add(alumno1)
//        listaAlumnosBBDD.add(alumno2)
//
//        var listaAlumnosProg = ArrayList<Alumnos>()
//        listaAlumnosProg.add(alumno3)
//
//        var listaProfesoresProg = ArrayList<Profesores>()
//        listaProfesoresProg.add(profesores1)
//
//        var listaProfesoresBBDD= ArrayList<Profesores>()
//        listaProfesoresBBDD.add(profesores2)
//
//        var asignaturasAlumnos1 = AsignaturasAlumnos(asignaturaBBDD, listaAlumnosBBDD)
//        var asignaturasProfesores2 = AsignaturasProfesores(asignaturaBBDD, listaProfesoresBBDD)
//
//        var asignaturasAlumnos2 = AsignaturasAlumnos(asignaturaProg, listaAlumnosProg)
//        var asignaturasProfesores1 = AsignaturasProfesores(asignaturaProg, listaProfesoresProg)
//
//
//
//
//
//
//        var dataRepository = DataRepository(this)
//
//
//        dataRepository.insertAsignaturasAlumnos(asignaturasAlumnos1)
//        dataRepository.insertAsignaturasAlumnos(asignaturasAlumnos2)
//
//        dataRepository.insertAsignaturasProfesores(asignaturasProfesores1)
//        dataRepository.insertAsignaturasProfesores(asignaturasProfesores2)


    }

    private fun verProfesores(asignatura: String){
        if (!asignatura.equals("Seleccione uno:")) {

            val asignaturaNombre = Bundle()
            asignaturaNombre.putString("asignatura", asignatura)


            frameLayoutFragmentProfesor = findViewById(R.id.frameLayoutProfesor)

            frameLayoutFragmentAlumnos = findViewById(R.id.frameLayoutAlumno)

            frameLayoutFragmentFicha = findViewById(R.id.frameLayoutFicha)

            frameLayoutFragmentProfesor?.removeAllViewsInLayout()
            frameLayoutFragmentAlumnos?.removeAllViewsInLayout()
            frameLayoutFragmentFicha?.removeAllViewsInLayout()



            listaFragmentProfesor = FragmentListaProfesor.newInstance()
            listaFragmentAlumno = FragmentListaAlumno.newInstance()
            listaFragmentAlumno!!.activityListener = activityListener

            listaFragmentProfesor!!.setArguments(asignaturaNombre)
            listaFragmentAlumno!!.setArguments(asignaturaNombre)

            fichaFragment = FragmentFichaAlumno()

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

        if (frameLayoutFragmentFicha !=null){
            // HORIZONTAL
            fragmentTransaction.add(R.id.frameLayoutProfesor, listaFragmentProfesor!!)
            fragmentTransaction.add(R.id.frameLayoutAlumno, listaFragmentAlumno!!)
            fragmentTransaction.add(R.id.frameLayoutFicha, fichaFragment!!)
        }
        else {
            fragmentTransaction.add(R.id.frameLayoutProfesor, listaFragmentProfesor!!)
            fragmentTransaction.add(R.id.frameLayoutAlumno, listaFragmentAlumno!!)
        }

            fragmentTransaction.commit()
        }
    }

    var activityListener = View.OnClickListener {
        if (frameLayoutFragmentFicha!=null) {
            fichaFragment!!.updateData(listaFragmentAlumno!!.itemSeleccionado)

        }else{
            val intent = Intent(this, alumnoFichaActivity::class.java).apply {
                putExtra("idAlumno", listaFragmentAlumno!!.itemSeleccionado?.id.toString())
            }
            startActivity(intent)
        }
    }

    private fun creacionActivity(){
        var dataRepository = DataRepository(this)

        //Rellenamos el array con al array que hemos recibido de la Query
        val spinner = findViewById<Spinner>(R.id.spinner)
        var pedidosGuardados = dataRepository.getAsignaturas()
        var ArraySpinner = ArrayList<String>()
        ArraySpinner.add("Seleccione uno:")
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
                    view: View?,
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

}