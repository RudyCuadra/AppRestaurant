package com.example.mvvmtest.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmtest.R
import com.example.mvvmtest.viewmodel.DataViewModel

class Contentfood : AppCompatActivity() {

    lateinit var viewModel: DataViewModel
    lateinit var idF: String
    lateinit var imagenurl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contentfood)

        viewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)

        //Pedimos los datos de la comida seleccionada
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
        val save = findViewById<Button>(R.id.btnGuardar)

        //Setiamos los datos de la comida
        txtNombre.setText(nombre)
        txtDesc.setText(descripcion)
        txtPrecio.setText(precio.toString())
        txtEst.setText(cantEstrells.toString())

        save.setOnClickListener {
            viewModel.updateRecord(this, this as Activity,idF, imagenurl)
        }

    }
}