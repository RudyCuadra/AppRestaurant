package com.example.mvvmtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtest.R
import com.example.mvvmtest.ui.modelo.Comidas
import com.example.mvvmtest.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val frutasObserver = Observer<List<Comidas>>{
            for (fruta in it){
                Log.e("Comida: ",fruta.nombreComida)
            }
        }

        viewModel.getListaComidasLiveData().observe(this,frutasObserver)
    }
}