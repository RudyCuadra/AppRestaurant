package com.example.mvvmtest.domain

import com.example.mvvmtest.data.FrutasDataSet
import com.example.mvvmtest.ui.modelo.Comidas

class FrutasUseCase {

    val frutasDataSet = FrutasDataSet()

    fun obtenerListaDeComidas(): List<Comidas>{
        return frutasDataSet.crearListaDeComidas()
    }
}