package com.example.fragmentjsonbbdd.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ProfesoresDao {
    @Insert
    fun insertAll(vararg profesores: Profesores)
}