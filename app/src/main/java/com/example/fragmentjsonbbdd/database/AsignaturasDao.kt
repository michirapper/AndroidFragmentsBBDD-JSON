package com.example.fragmentjsonbbdd.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface AsignaturasDao {

    @Query("Select * from asignaturas")
    fun getAsignaturas(): Array<Asignaturas>

    @Insert
    fun insertAll(asignaturas: List<Asignaturas>)

    @Insert
    fun insertAll(vararg asignaturas: Asignaturas)
}