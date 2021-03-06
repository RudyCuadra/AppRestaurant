package com.example.mvvmtest.view


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.viewmodel.DataViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: DataViewModel
    lateinit var recyclerViewF: RecyclerView
    lateinit var txtTextoInit: TextView
    lateinit var txtTextoSecond: TextView
    lateinit var btnFloatingActionButton: FloatingActionButton

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        //setTheme(R.style.Theme_MVVMTest_NoActionBar) // Splash theme inicio de la aplicación
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        recyclerViewF = findViewById(R.id.rvfood) as RecyclerView
        txtTextoInit = findViewById(R.id.txtGreetngTime) as TextView
        txtTextoSecond = findViewById(R.id.txtGreetingSecond) as TextView
        btnFloatingActionButton = findViewById(R.id.btnFab) as FloatingActionButton

        viewModel.viewRecord(this,recyclerViewF,txtTextoInit,txtTextoSecond)

        btnFloatingActionButton.setOnClickListener {
            val intent = Intent(this,Contentfood::class.java)
            intent.putExtra("Activity","NEW")
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }



}