package com.example.mvvmtest.viewmodel

import android.content.ContentValues
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
                "('LASAÑA',250,'es un tipo de pasta. Se suele servir en láminas superpuestas intercaladas con capas de ingredientes al gusto, más frecuentemente carne en salsa boloñesa y bechamel.','android.resource://com.example.mvvmtest/drawable/lasana',4.0)," +
                "('ALBÓNDIGAS DE PAVO',240,'Sorprende a tu familia con éstas ricas y saludables albóndigas de pollo con salsa de queso parmesano. La salsa esta hecha con Yoghurt FAGE Total® 0% que le aporta un delicioso sabor y una consistencia cremosa','android.resource://com.example.mvvmtest/drawable/albondigas',3.0)," +
                "('PECHUGAS DE POLLO',210,'Esta rica salsa hará que cualquier pieza de pollo quede con una textura cremosa y sabor suculento.','android.resource://com.example.mvvmtest/drawable/pechugas',2.0)," +
                "('BURRITO NORTEÑO',190,'Prueba este rico burrito que aparte de sencillo les encantara a todo en casa, es una rica combinación de frijoles, carne asada, guacamole y una rica salsa de chile de árbol. No dejes de probarla.','android.resource://com.example.mvvmtest/drawable/burrito',3.5),"+
                "('SALMÓN EN SALSA',230,'El cilantro le da un toque especial a uno de los pescados más ricos y saludables. ','android.resource://com.example.mvvmtest/drawable/salmon',5.0)," +
                "('PULPO A LA MEXICANA',500,'Si se trata de llevarla a la playa con un solo bocado, puedes lograrlo con estas 3 recetas frescas y mexicanísimas.','android.resource://com.example.mvvmtest/drawable/pulpo',4.0)," +
                "('POLLO RELLENO CON TOCINO',210,'Si de sabor se trata, este rico plato fuerte conquista hasta a la mamá más exigente. Lo mejor es que no tienes que ser un top chef para lograrlo.','android.resource://com.example.mvvmtest/drawable/tocino',2.0)," +
                "('CHULETAS CON SALSA DE MOSTAZA',210,'Disfruta junto con ella de una salsa agridulce, que marida perfecto con la carne de cerdo. Satisfacción garantizada.','android.resource://com.example.mvvmtest/drawable/chuletas',3.0);"

        p0?.execSQL(CREATE_DATABASE_TABLE)
        p0?.execSQL(INSERT_DATA)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //Metodo para insertar datos
    fun addFood(food: Comida): Long{
        val db = this.writableDatabase
        //content resolver - to map
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME,food.name)
        contentValues.put(KEY_PRICE,food.precio)
        contentValues.put(KEY_DESC,food.descripcion)
        contentValues.put(KEY_IMG,food.imagen)
        contentValues.put(KEY_STARS,food.estrellas)

        val success = db.insert(TABLE_FOOD, null, contentValues)
        db.close()
        return success
    }

    //Metodo para actualizar datos
    fun updateFood(food: Comida):Int{
        val db = this.writableDatabase
        //content resolver - to map
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME,food.name)
        contentValues.put(KEY_PRICE,food.precio)
        contentValues.put(KEY_DESC,food.descripcion)
        contentValues.put(KEY_IMG,food.imagen)
        contentValues.put(KEY_STARS,food.estrellas)

        //Actualizando registro
        val success = db.update(TABLE_FOOD, contentValues,"id="+food.id,null)
        // El segundo argumento es una cadena que contiene nullColumnHack
        db.close() // Cerramos la conexion de la base de datos
        return success
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

    //metodo para eliminar registro
    fun deleteFood(food: Comida): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, food.id)
        //Eliminando registro
        val sucess = db.delete(TABLE_FOOD, "id="+food.id, null)
        db.close()
        return sucess
    }

}