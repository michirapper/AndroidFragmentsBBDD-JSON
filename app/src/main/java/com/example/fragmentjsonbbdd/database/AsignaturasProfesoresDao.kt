package com.example.fragmentjsonbbdd.database

import androidx.room.*

@Dao
interface AsignaturasProfesoresDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: AsignaturasProfesoresCrossRef)

    @Transaction
    @Query("SELECT * FROM asignaturas WHERE asignaturasId = :asignaturasId")
    fun getProfesorOne(asignaturasId: Int): Array<AsignaturasProfesores>

}