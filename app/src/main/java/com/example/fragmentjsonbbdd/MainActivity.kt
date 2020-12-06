package com.example.fragmentjsonbbdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentjsonbbdd.database.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rellenarAsignaturas()
    }

    private fun rellenarAsignaturas() {
        var asignatura1 = Asignaturas(1, "BBDD")
        var asignatura2 = Asignaturas(2, "Programacion")

        var listaAsignaturas1 = ArrayList<Asignaturas>()
        listaAsignaturas1.add(asignatura1)

        var listaAsignaturas2 = ArrayList<Asignaturas>()
        listaAsignaturas2.add(asignatura2)

        var listaAsignaturas3 = ArrayList<Asignaturas>()
        listaAsignaturas3.add(asignatura1)
        listaAsignaturas3.add(asignatura2)


        var profesores1 = Profesores(1, "Antonio", "Mendoza")
        var profesores2 = Profesores(2, "Manuel", "Perez")

        var profesoresAsignaturas1 = ProfesoresAsignaturas(profesores1,listaAsignaturas1)
        var profesoresAsignaturas2 = ProfesoresAsignaturas(profesores2,listaAsignaturas2)


        var alumno1 = Alumnos(1,"Alumno1", "ApellidoAlumno1")
        var alumno2 = Alumnos(2,"Alumno2", "ApellidoAlumno2")
        var alumno3 = Alumnos(3,"Alumno3", "ApellidoAlumno3")

        var alumnosAsignaturas2 = AlumnosAsignaturas(alumno2,listaAsignaturas1)
        var alumnosAsignaturas3 = AlumnosAsignaturas(alumno3,listaAsignaturas2)
        var alumnosAsignaturas1 = AlumnosAsignaturas(alumno1,listaAsignaturas3)


        var dataRepository = DataRepository(this)
        dataRepository.insertProfesoresAsignaturas(profesoresAsignaturas1)
        dataRepository.insertProfesoresAsignaturas(profesoresAsignaturas2)

        dataRepository.insertAlumnosAsignaturas(alumnosAsignaturas2)
        dataRepository.insertAlumnosAsignaturas(alumnosAsignaturas3)
        dataRepository.insertAlumnosAsignaturas(alumnosAsignaturas1)

    }


}