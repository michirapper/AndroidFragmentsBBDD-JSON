package com.example.fragmentjsonbbdd.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface AlumnosAsignaturasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: AlumnosAsignaturasCrossRef)
}