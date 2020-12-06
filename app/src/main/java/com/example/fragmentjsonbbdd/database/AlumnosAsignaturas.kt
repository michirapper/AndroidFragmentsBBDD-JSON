package com.example.fragmentjsonbbdd.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class AlumnosAsignaturas(
    @Embedded var alumnos: Alumnos,

    @Relation(
        parentColumn = "alumnosId",
        entity = Asignaturas::class,
        entityColumn = "asignaturasId",
        associateBy = Junction(value = AlumnosAsignaturasCrossRef::class,
            parentColumn = "alumnosId",
            entityColumn = "asignaturasId")
    )

    var asignaturas: List<Asignaturas>
)

@Entity(primaryKeys = ["alumnosId", "asignaturasId"])
data class AlumnosAsignaturasCrossRef(
    val alumnosId: Int,
    val asignaturasId: Int
)