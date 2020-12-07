package com.example.fragmentjsonbbdd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.fragmentjsonbbdd.R
import com.example.fragmentjsonbbdd.fragments.FragmentFichaAlumno

class alumnoFichaActivity : AppCompatActivity() {
    var frameLayoutFicha: FrameLayout? = null
    var fichaFragment: FragmentFichaAlumno? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumnos_ficha)

        val messeage = intent.getStringExtra("idAlumno")

        Toast.makeText(this,messeage, Toast.LENGTH_SHORT).show()

        val idAlumno = Bundle()
        idAlumno.putString("idAlumno", messeage)


        frameLayoutFicha = findViewById(R.id.frameLayoutFragment)
        fichaFragment = FragmentFichaAlumno()

        fichaFragment!!.setArguments(idAlumno)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayoutFragment, fichaFragment!!)

        fragmentTransaction.commit()
    }
}