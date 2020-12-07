package com.example.fragmentjsonbbdd.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlumnosDao {
    @Insert
    fun insertAll(vararg alumnos: Alumnos)
    @Insert
    fun insertAll(alumnos: List<Alumnos>)

    @Query("SELECT * FROM alumnos WHERE alumnosId = :alumnoId")
    fun getAlumnoOne(alumnoId: Int): Array<Alumnos>
}