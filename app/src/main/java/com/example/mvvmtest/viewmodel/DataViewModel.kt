package com.example.mvvmtest.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.model.Comida
import com.example.mvvmtest.view.Contentfood
import com.example.mvvmtest.view.FoodAdapter
import com.example.mvvmtest.view.MainActivity
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DataViewModel():ViewModel() {


    fun viewRecord(context: Context, recyclerFood: RecyclerView,textView1: TextView,textView2: TextView){

        loadGreetings(context,textView1,textView2)

        //PROCESO DE PEDIR PERMISO DE ALMACENAMIENTO
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else { ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1) } }
        //FIN DE PEDIR PERMISO DE ALMACENAMIENTO

        //PROCESO DE PEDIR PERMISO DE ALMACENAMIENTO
        if (ContextCompat.checkSelfPermission(context as Activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else { ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1) } }
        //FIN DE PEDIR PERMISO DE ALMACENAMIENTO

        //creando la instancia de la clase DatabaseHandler
        val databaseHandler: DatabaseHandler= DatabaseHandler(context)
        //llamar al método viewEmployee de la clase DatabaseHandler para leer los registros
        val food: List<Comida> = databaseHandler.viewFoods()
        //creando RecyclerView personalizado
        val foodAdapter = FoodAdapter(context as Activity, food)
        recyclerFood.layoutManager = LinearLayoutManager(context)
        recyclerFood.isHorizontalScrollBarEnabled
        recyclerFood.isVerticalScrollBarEnabled
        recyclerFood.setHasFixedSize(true)
        recyclerFood.adapter = foodAdapter
    }

    fun updateRecord(context: Context,view:Activity, id: String, imagen: String){
        val edNombre = view.findViewById(R.id.edNombre) as EditText
        val edDesc = view.findViewById<EditText>(R.id.edDesc)
        val edPrec = view.findViewById<EditText>(R.id.edPrecio)
        val edCantE= view.findViewById<EditText>(R.id.edEst)

        val nombre = edNombre.text.toString()
        val descripcion = edDesc.text.toString()
        val precio = edPrec.text.toString()
        val estrells = edCantE.text.toString()

        // creando la instancia de la clase DatabaseHandler
        val databaseHandler: DatabaseHandler = DatabaseHandler(context)
        if(nombre.trim()!="" && descripcion.trim()!="" && precio.trim()!="" && estrells.trim()!=""){
            // llamando al método updateEmployee de la clase DatabaseHandler para actualizar el registro
            val status = databaseHandler.updateFood(Comida(id.toInt(),nombre, precio.toFloat(),descripcion,imagen,estrells.toFloat()))
            if(status > -1){
                Toast.makeText(context,"Registro Actualizado", Toast.LENGTH_LONG).show()

                val intent = Intent(context, MainActivity::class.java)
                view.finish()
                view.startActivity(intent)
                //context.startActivity(intent)
            }
        }else{
            Toast.makeText(context,"Llene todos los campos", Toast.LENGTH_LONG).show()
        }
    }

    fun loadData(context: Context, view: View){

    }

    @SuppressLint("SimpleDateFormat", "NewApi")
    fun loadGreetings(context: Context, texto1:TextView, texto2:TextView){
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH")
        val formatted = current.format(formatter)
        //Toast.makeText(context, "HORA: $formatted",Toast.LENGTH_LONG).show()

        if(formatted.toInt()<=11){
            texto1.text = "Buenos días"
            texto2.text = "Es hora de un buen desayuno"
        }else if(formatted.toInt()<=17){
            texto1.text = "Buenas Tardes"
            texto2.text = "Es hora de un buen almuerzo"
        }else if(formatted.toInt()<=24){
            texto1.text = "Buenas Noches"
            texto2.text = "Es hora de una buena cena"
        }
    }

    fun openImage(context: Context, id: Long): Uri{
        val file = File(context.filesDir, id.toString())

        return if(file.exists()){
            Uri.fromFile(file)
        }else{
            Uri.parse("android.resource://com.example.mvvmtest/drawable/unnamed")
        }
    }


}