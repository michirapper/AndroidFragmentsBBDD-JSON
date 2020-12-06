package com.example.fragmentjsonbbdd.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AlumnosDao {
    @Insert
    fun insertAll(vararg alumnos: Alumnos)
}