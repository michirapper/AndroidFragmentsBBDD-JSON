package com.example.fragmentjsonbbdd.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val asignaturasDao: AsignaturasDao? = AppDatabase.getInstance(context)?.asignaturasDao()
    private val profesoresDao: ProfesoresDao? = AppDatabase.getInstance(context)?.profesoresDao()
    private val profesoresAsignaturasDao: ProfesoresAsignaturasDao? = AppDatabase.getInstance(context)?.profesoresAsignaturasDao()

    fun insert(profesoresAsignaturas: ProfesoresAsignaturas):Int{
        if (profesoresDao != null && asignaturasDao !=null && profesoresAsignaturasDao!= null) {
            return InsertProfesoresAsignaturasAsyncTask(profesoresDao, asignaturasDao, profesoresAsignaturasDao).execute(profesoresAsignaturas).get()
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


}