package com.example.mvvmtest.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: DataViewModel
    lateinit var recyclerViewF: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        recyclerViewF = findViewById(R.id.rvfood) as RecyclerView
        recyclerViewF.layoutManager = LinearLayoutManager(this)
        recyclerViewF.isHorizontalScrollBarEnabled
        recyclerViewF.isVerticalScrollBarEnabled
        recyclerViewF.setHasFixedSize(true)

        viewModel.viewRecord(this,recyclerViewF)
    }

    fun viewRecord(view: View){
        viewModel.viewRecord(this,recyclerViewF)
    }
}