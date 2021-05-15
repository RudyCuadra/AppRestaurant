package com.example.mvvmtest.viewmodel

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.mvvmtest.model.Comida

class DatabaseHandler (context: Context):  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "RestaurantDB"
        private val TABLE_FOOD = "Food"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_PRICE = "precio"
        private val KEY_DESC = "descripcion"
        private val KEY_IMG = "imagen"
        private val KEY_STARS= "estrellas"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_DATABASE_TABLE = ("CREATE TABLE $TABLE_FOOD ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$KEY_NAME VARCHAR(100), " +
                "$KEY_PRICE DECIMAL(5,2), " +
                "$KEY_DESC VARCHAR(256), " +
                "$KEY_IMG VARCHAR(300), " +
                "$KEY_STARS DECIMAL(1,1));")

        val INSERT_DATA = "INSERT INTO $TABLE_FOOD($KEY_NAME,$KEY_PRICE,$KEY_DESC,$KEY_IMG,$KEY_STARS) VALUES" +
                "('Lasaña',250,'es un tipo de pasta. Se suele servir en láminas superpuestas intercaladas con capas de ingredientes al gusto, más frecuentemente carne en salsa boloñesa y bechamel.','',4.0)," +
                "('ALBÓNDIGAS DE PAVO',240,'Sorprende a tu familia con éstas ricas y saludables albóndigas de pollo con salsa de queso parmesano. La salsa esta hecha con Yoghurt FAGE Total® 0% que le aporta un delicioso sabor y una consistencia cremosa','',3.0)," +
                "('PECHUGAS DE POLLO',210,'Esta rica salsa hará que cualquier pieza de pollo quede con una textura cremosa y sabor suculento.','',2.0)," +
                "('ALMENDRADO',190,'Prepara esta salsa tersa, ideal para acompañar desde carne de cerdo hasta pollo. Este platillo clásico tan sabroso que dejará el plato más que limpio.','',3.5);"

        p0?.execSQL(CREATE_DATABASE_TABLE)
        p0?.execSQL(INSERT_DATA)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    // método para leer datos
    fun viewFoods(): List<Comida>{
        val foodList: ArrayList<Comida> = ArrayList<Comida>()
        val selectQuery = "SELECT * FROM $TABLE_FOOD"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var precio: Float
        var descripcion: String
        var imagen: String
        var estrellas: Float

        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                precio = cursor.getFloat(cursor.getColumnIndex("precio"))
                descripcion = cursor.getString(cursor.getColumnIndex("descripcion"))
                imagen = cursor.getString(cursor.getColumnIndex("imagen"))
                estrellas = cursor.getFloat(cursor.getColumnIndex("estrellas"))
                val food = Comida(id = id, name = name, precio = precio, descripcion = descripcion,
                imagen = imagen, estrellas = estrellas)
                foodList.add(food)
            }while (cursor.moveToNext())
        }
        return foodList
    }

}