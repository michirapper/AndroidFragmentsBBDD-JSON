package com.example.fragmentjsonbbdd.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val asignaturasDao: AsignaturasDao? = AppDatabase.getInstance(context)?.asignaturasDao()
    private val profesoresDao: ProfesoresDao? = AppDatabase.getInstance(context)?.profesoresDao()
    private val profesoresAsignaturasDao: ProfesoresAsignaturasDao? = AppDatabase.getInstance(context)?.profesoresAsignaturasDao()

    fun insert(pedidoProducto: PedidoProducto):Int{
        if (pedidoDao != null && productoDao !=null && pedidoProductoDao!= null) {
            return InsertPedidoProductoAsyncTask(pedidoDao, productoDao, pedidoProductoDao).execute(pedidoProducto).get()
        }
        return -1
    }

    fun getAsignaturas(): List<Asignaturas>{
        return GetAsignaturas(asignaturasDao!!).execute().get()
    }

    private class InsertAsyncTask(private val asignaturasDao: AsignaturasDao) : AsyncTask<Asignaturas, Void, Int>() {
        override fun doInBackground(vararg asignaturas: Asignaturas?): Int? {
            try {
                for (asignaturas in asignaturas) {
                    if (asignaturas != null) asignaturasDao.insertAll(asignaturas)
                }
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    private class GetAsignaturas(private val asignaturasDao: AsignaturasDao) :AsyncTask<Void, Void, List<Asignaturas>>(){
        override fun doInBackground(vararg params: Void?): List<Asignaturas> {
            return asignaturasDao.getAsignaturas()
        }
    }

}