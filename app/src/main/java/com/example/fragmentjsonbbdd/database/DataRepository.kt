package com.example.fragmentjsonbbdd.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val asignaturasDao: AsignaturasDao? = AppDatabase.getInstance(context)?.asignaturasDao()
    private val profesoresDao: ProfesoresDao? = AppDatabase.getInstance(context)?.profesoresDao()
    private val alumnosDao: AlumnosDao? = AppDatabase.getInstance(context)?.alumnosDao()
    private val profesoresAsignaturasDao: ProfesoresAsignaturasDao? = AppDatabase.getInstance(context)?.profesoresAsignaturasDao()
    private val asignaturasAlumnosDao: AsignaturasAlumnosDao?= AppDatabase.getInstance(context)?.alumnosAsignaturasDao()

    fun insertProfesoresAsignaturas(profesoresAsignaturas: ProfesoresAsignaturas):Int{
        if (profesoresDao != null && asignaturasDao !=null && profesoresAsignaturasDao!= null) {
            return InsertProfesoresAsignaturasAsyncTask(profesoresDao, asignaturasDao, profesoresAsignaturasDao).execute(profesoresAsignaturas).get()
        }
        return -1
    }

    fun insertAsignaturasAlumnos(asignaturasAlumnos: AsignaturasAlumnos):Int{
        if (asignaturasDao != null && alumnosDao !=null && asignaturasAlumnosDao!= null) {
            return InsertAsignaturasAlumnosAsyncTask(asignaturasDao, alumnosDao, asignaturasAlumnosDao).execute(asignaturasAlumnos).get()
        }
        return -1
    }

    fun getAsignaturas(): Array<Asignaturas>{
        return GetAsignaturas(asignaturasDao!!).execute().get()
    }
    fun getProfesorOne(idAsignatura: Int): List<ProfesoresAsignaturas>{
        return GetProfesorOne(profesoresAsignaturasDao!!, idAsignatura).execute().get()
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

    private class InsertAsignaturasAlumnosAsyncTask(private val asignaturasDao: AsignaturasDao, private val alumnosDao: AlumnosDao, private val asignaturasAlumnosDao: AsignaturasAlumnosDao): AsyncTask<AsignaturasAlumnos, Void, Int>(){
        override fun doInBackground(vararg asignaturasAlumnos: AsignaturasAlumnos?): Int {
            try{
                for (asignaturasAlumnos in asignaturasAlumnos){
                    if (asignaturasAlumnos !=null){
                        asignaturasDao.insertAll(asignaturasAlumnos.asignaturas)
                        alumnosDao.insertAll(asignaturasAlumnos.alumnos)
                        for (alumnos in asignaturasAlumnos.alumnos){
                            asignaturasAlumnosDao.insert(AsignaturasAlumnosCrossRef(asignaturasAlumnos.asignaturas.asignaturasId, alumnos.alumnosId))
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

    private class GetAsignaturas(private val asignaturasDao: AsignaturasDao) :AsyncTask<Void, Void, Array<Asignaturas>>(){
        override fun doInBackground(vararg params: Void?): Array<Asignaturas> {
            return asignaturasDao.getAsignaturas()
        }
    }
    private class GetProfesorOne(private val profesoresAsignaturasDao: ProfesoresAsignaturasDao, private val idAsignatura: Int) :AsyncTask<Void, Void, List<ProfesoresAsignaturas>>(){
        override fun doInBackground(vararg params: Void?): List<ProfesoresAsignaturas> {
            return profesoresAsignaturasDao.getProfesorOne(idAsignatura)
        }
    }


}