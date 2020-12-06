package com.example.fragmentjsonbbdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentjsonbbdd.database.Asignaturas
import com.example.fragmentjsonbbdd.database.DataRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rellenarAsignaturas()
    }

    private fun rellenarAsignaturas() {
        var asignatura1 = Asignaturas(1, "BBDD")
        var asignatura2 = Asignaturas(2, "Programacion")

        var dataRepository = DataRepository(this)
        dataRepository.insert(asignatura1)
        dataRepository.insert(asignatura2)
    }

}