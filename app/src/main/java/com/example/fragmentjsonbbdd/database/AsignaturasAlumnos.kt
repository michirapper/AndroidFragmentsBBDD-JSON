package com.example.fragmentjsonbbdd.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class AsignaturasAlumnos(
    @Embedded var asignaturas: Asignaturas,

    @Relation(
        parentColumn = "asignaturasId",
        entity = Alumnos::class,
        entityColumn = "alumnosId",
        associateBy = Junction(value = AsignaturasAlumnosCrossRef::class,
            parentColumn = "asignaturasId",
            entityColumn = "alumnosId")
    )

    var alumnos: List<Alumnos>
)

@Entity(primaryKeys = ["asignaturasId", "alumnosId"])
data class AsignaturasAlumnosCrossRef(
    val asignaturasId: Int,
    val alumnosId: Int
)