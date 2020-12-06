package com.example.fragmentjsonbbdd.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val asignaturasDao: AsignaturasDao? = AppDatabase.getInstance(context)?.asignaturasDao()
    private val profesoresDao: ProfesoresDao? = AppDatabase.getInstance(context)?.profesoresDao()
    private val alumnosDao: AlumnosDao? = AppDatabase.getInstance(context)?.alumnosDao()
    private val profesoresAsignaturasDao: ProfesoresAsignaturasDao? = AppDatabase.getInstance(context)?.profesoresAsignaturasDao()
    private val alumnosAsignaturasDao: AlumnosAsignaturasDao?= AppDatabase.getInstance(context)?.alumnosAsignaturasDao()

    fun insertProfesoresAsignaturas(profesoresAsignaturas: ProfesoresAsignaturas):Int{
        if (profesoresDao != null && asignaturasDao !=null && profesoresAsignaturasDao!= null) {
            return InsertProfesoresAsignaturasAsyncTask(profesoresDao, asignaturasDao, profesoresAsignaturasDao).execute(profesoresAsignaturas).get()
        }
        return -1
    }
    fun insertAlumnosAsignaturas(alumnosAsignaturas: AlumnosAsignaturas):Int{
        if (alumnosDao != null && asignaturasDao !=null && alumnosAsignaturasDao!= null) {
            return InsertAlumnosAsignaturaAsyncTask(alumnosDao, asignaturasDao, alumnosAsignaturasDao).execute(alumnosAsignaturas).get()
        }
        return -1
    }

    private class InsertProfesoresAsignaturasAsyncTask(private val profesoresDao: ProfesoresDao, private val asignaturasDao: AsignaturasDao, private val profesoresAsignaturasDao: ProfesoresAsignaturasDao): AsyncTask<ProfesoresAsignaturas, Void, Int>(){
        override fun doInBackground(vararg profesoresAsignaturas: ProfesoresAsignaturas?): Int {
            try{
                for (profesoresAsignaturas in profesoresAsignaturas){
                    if (profesoresAsignaturas !=null){
                        profesoresDao.insertAll(profesoresAsignaturas.profesores)
                        asignaturasDao.insertAll(profesoresAsignaturas.asignaturas)
                        for (asignaturas in profesoresAsignaturas.asignaturas){
                            profesoresAsignaturasDao.insert(ProfesoresAsignaturasCrossRef(profesoresAsignaturas.profesores.profesoresId, asignaturas.asignaturasId))
                        }
                    }
                }

                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    private class InsertAlumnosAsignaturaAsyncTask(private val alumnosDao: AlumnosDao, private val asignaturasDao: AsignaturasDao, private val alumnosAsignaturasDao: AlumnosAsignaturasDao): AsyncTask<AlumnosAsignaturas, Void, Int>(){
        override fun doInBackground(vararg alumnosAsignaturas: AlumnosAsignaturas?): Int {
            try{
                for (alumnosAsignaturas in alumnosAsignaturas){
                    if (alumnosAsignaturas !=null){
                        alumnosDao.insertAll(alumnosAsignaturas.alumnos)
                        //asignaturasDao.insertAll(alumnosAsignaturas.asignaturas)
                        for (asignaturas in alumnosAsignaturas.asignaturas){
                            alumnosAsignaturasDao.insert(AlumnosAsignaturasCrossRef(alumnosAsignaturas.alumnos.alumnosId, asignaturas.asignaturasId))
                        }
                    }
                }

                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }


}