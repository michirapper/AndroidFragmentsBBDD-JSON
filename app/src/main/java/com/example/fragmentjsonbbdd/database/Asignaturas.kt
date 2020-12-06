package com.example.fragmentjsonbbdd.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Asignaturas(
    @PrimaryKey val id: Int,
    val nombre: String?
)