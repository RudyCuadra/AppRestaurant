package com.example.mvvmtest.view

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmtest.R
import com.example.mvvmtest.viewmodel.DataViewModel

class Contentfood : AppCompatActivity() {

    lateinit var viewModel: DataViewModel
    lateinit var idF: String
    lateinit var imagenurl: String
    lateinit var img: ImageView
    lateinit var save: Button
    lateinit var changeImg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contentfood)

        viewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)

        //Pedimos los datos de la comida seleccionada
        val activityPrevious = intent.getStringExtra("Activity")
        save = findViewById<Button>(R.id.btnGuardar)
        changeImg = findViewById<TextView>(R.id.txtCambiarI)
        img = findViewById<ImageView>(R.id.imgFood)
        val tituloView = findViewById<TextView>(R.id.txtTitulo)

        if(activityPrevious=="NEW"){
            changeImg.text = "Añadir imagen"
            tituloView.text = "Nueva Comida"
        }else if(activityPrevious=="UPDATE"){
            idF = intent.getStringExtra("id").toString()
            val nombre = intent.getStringExtra("nombre")
            val descripcion = intent.getStringExtra("Descripcion")
            val precio = intent.getFloatExtra("Precio",0.0F)
            val cantEstrells = intent.getFloatExtra("cantEst",0.0F)
            imagenurl = intent.getStringExtra("imagenurl").toString()

            val txtNombre = findViewById<EditText>(R.id.edNombre)
            val txtDesc = findViewById<EditText>(R.id.edDesc)
            val txtPrecio = findViewById<EditText>(R.id.edPrecio)
            val txtEst = findViewById<EditText>(R.id.edEst)

            //Setiamos los datos de la comida
            txtNombre.setText(nombre)
            txtDesc.setText(descripcion)
            txtPrecio.setText(precio.toString())
            txtEst.setText(cantEstrells.toString())
            img.setImageURI(Uri.parse(imagenurl))
        }

        save.setOnClickListener {
            if(activityPrevious=="NEW"){
                viewModel.addRecord(this,this as Activity, imagenurl)
            }else if(activityPrevious=="UPDATE"){
                viewModel.updateRecord(this, this as Activity,idF, imagenurl)
            }
        }

        changeImg.setOnClickListener {
            viewModel.loadImageGallery(this as Activity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 100){
            imagenurl = data?.data.toString()
            //Toast.makeText(this, "URL: $imagenurl", Toast.LENGTH_SHORT).show()
            img.setImageURI(Uri.parse(imagenurl))
        }

    }
}