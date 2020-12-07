package com.example.fragmentjsonbbdd.database

import androidx.room.*

@Dao
interface ProfesoresAsignaturasDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: ProfesoresAsignaturasCrossRef)

    @Transaction
    @Query("SELECT * FROM profesores WHERE profesoresId  = :asignaturasId")
    fun getProfesorOne(asignaturasId: Int): List<ProfesoresAsignaturas>

}