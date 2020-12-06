package com.example.fragmentjsonbbdd.database

import androidx.room.*

@Dao
interface ProfesoresAsignaturasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: AsignaturasProfesoresCrossRef)

    @Transaction
    @Query("SELECT * FROM profesores")
    fun getProfesores(): List<ProfesoresAsignaturasDao>


}