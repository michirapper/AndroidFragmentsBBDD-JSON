package com.example.fragmentjsonbbdd.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfesoresDao {
    @Insert
    fun insertAll(vararg profesores: Profesores)
    @Insert
    fun insertAll(profesores: List<Profesores>)
}