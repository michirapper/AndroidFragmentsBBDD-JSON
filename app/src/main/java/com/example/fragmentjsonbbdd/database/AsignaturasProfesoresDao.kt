package com.example.fragmentjsonbbdd.database

import androidx.room.*

@Dao
interface AsignaturasProfesoresDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: AsignaturasProfesoresCrossRef)

}