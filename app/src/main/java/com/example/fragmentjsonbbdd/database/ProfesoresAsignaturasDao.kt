package com.example.fragmentjsonbbdd.database

import androidx.room.*

@Dao
interface ProfesoresAsignaturasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: ProfesoresAsignaturasCrossRef)

    @Transaction
    @Query("SELECT * FROM profesores")
    fun getProfesores(): List<ProfesoresAsignaturasDao>


}