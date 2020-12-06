package com.example.fragmentjsonbbdd.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profesores(
    @PrimaryKey val profesoresId: Int,
    val codigo: Int?,
    val nombre: String?,
    val apellido: String?
)