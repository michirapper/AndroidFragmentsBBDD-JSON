package com.example.fragmentjsonbbdd.database

import androidx.room.*

data class AsignaturasProfesores(
    @Embedded var asignaturas: Asignaturas,

    @Relation(
        parentColumn = "asignaturasId",
        entity = Profesores::class,
        entityColumn = "profesoresId",
        associateBy = Junction(value = AsignaturasProfesoresCrossRef::class,
            parentColumn = "asignaturasId",
            entityColumn = "profesoresId")
    )

    var profesores: List<Profesores>
)

@Entity(primaryKeys = ["asignaturasId", "profesoresId"])
data class AsignaturasProfesoresCrossRef(
    val asignaturasId: Int,
    val profesoresId: Int
)

