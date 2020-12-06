package com.example.fragmentjsonbbdd.database

import androidx.room.*

data class ProfesoresAsignaturas(
    @Embedded var profesores: Profesores,

    @Relation(
        parentColumn = "profesoresId",
        entity = Asignaturas::class,
        entityColumn = "asignaturasId",
        associateBy = Junction(value = ProfesoresAsignaturasCrossRef::class,
            parentColumn = "profesoresId",
            entityColumn = "asignaturasId")
    )

    var asignaturas: List<Asignaturas>
)

@Entity(primaryKeys = ["profesoresId", "productId"])
data class ProfesoresAsignaturasCrossRef(
    val profesoresId: Int,
    val asignaturasId: Int
)