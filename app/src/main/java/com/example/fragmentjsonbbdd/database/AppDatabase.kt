package com.example.fragmentjsonbbdd.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Asignaturas::class, Profesores::class,Alumnos::class, ProfesoresAsignaturasCrossRef::class, AlumnosAsignaturasCrossRef::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun asignaturasDao(): AsignaturasDao
    abstract fun profesoresDao(): ProfesoresDao
    abstract fun alumnosDao(): AlumnosDao
    abstract fun profesoresAsignaturasDao(): ProfesoresAsignaturasDao
    abstract fun alumnosAsignaturasDao(): AlumnosAsignaturasDao

    companion object {
        private const val DATABASE_NAME = "alumnos_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )./*addMigrations(MIGRATION_1_2).*/build()
            }
            return INSTANCE
        }

       /* val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE 'Product' ('productId' INTEGER NOT NULL, 'nombre' TEXT, 'precio' INTEGER, PRIMARY KEY('productId'))")
            }
        }*/

    }
}