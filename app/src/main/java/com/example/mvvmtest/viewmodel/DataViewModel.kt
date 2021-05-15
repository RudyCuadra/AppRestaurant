package com.example.mvvmtest.viewmodel

import android.app.Activity
import android.content.Context
import android.widget.ListView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.model.Comida
import com.example.mvvmtest.view.FoodAdapter
import java.io.File

class DataViewModel:ViewModel() {



    fun viewRecord(context: Context, recyclerFood: RecyclerView){
        //creando la instancia de la clase DatabaseHandler
        val databaseHandler: DatabaseHandler= DatabaseHandler(context)
        //llamar al método viewEmployee de la clase DatabaseHandler para leer los registros
        val food: List<Comida> = databaseHandler.viewFoods()
        //creando RecyclerView personalizado
        val foodAdapter = FoodAdapter(context as Activity, food)
        recyclerFood.adapter = foodAdapter
    }

    fun openImage(context: Context, id: Long){
        val file = File(context.filesDir, id.toString())
    }
}