package com.example.fragmentjsonbbdd.database

import androidx.room.*

@Dao
interface ProfesoresAsignaturasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: ProfesoresAsignaturasCrossRef)
}