package com.example.mvvmtest.viewmodel

import android.app.Activity
import android.content.Context
import android.widget.ListView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.model.Comida
import com.example.mvvmtest.view.FoodAdapter

class DataViewModel:ViewModel() {



    fun viewRecord(context: Context, recyclerFood: RecyclerView){
        //creando la instancia de la clase DatabaseHandler
        val databaseHandler: DatabaseHandler= DatabaseHandler(context)
        //llamar al método viewEmployee de la clase DatabaseHandler para leer los registros
        val food: List<Comida> = databaseHandler.viewFoods()
        /*val foodArrayId = Array<String>(food.size){"0"}
        val foodArrayName = Array<String>(food.size){"null"}
        val foodArrayPrecio = Array<String>(food.size){"0.0"}
        val foodArrayDescripcion = Array<String>(food.size){"null"}
        val foodArrayImagen = Array<String>(food.size){"null"}
        val foodArrayEstrellas = Array<String>(food.size){"0"}
        var index = 0*/
        //creando RecyclerView personalizado
        val foodAdapter = FoodAdapter(context as Activity, food)
        recyclerFood.adapter = foodAdapter
    }
}