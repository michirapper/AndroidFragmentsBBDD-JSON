package com.example.fragmentjsonbbdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentjsonbbdd.database.Asignaturas
import com.example.fragmentjsonbbdd.database.DataRepository
import com.example.fragmentjsonbbdd.database.Profesores
import com.example.fragmentjsonbbdd.database.ProfesoresAsignaturas

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

        var profesores1 = Profesores(1,1, "Antonio", "Mendoza")
        var profesores2 = Profesores(2,2, "Manuel", "Perez")

        var profesoresAsignaturas1 = ProfesoresAsignaturas(profesores1,listaAsignaturas1)
        var profesoresAsignaturas2 = ProfesoresAsignaturas(profesores2,listaAsignaturas2)

        var dataRepository = DataRepository(this)
        dataRepository.insert(profesoresAsignaturas1)
        dataRepository.insert(profesoresAsignaturas2)
    }

}