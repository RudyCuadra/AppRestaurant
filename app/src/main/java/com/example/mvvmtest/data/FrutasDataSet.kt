package com.example.mvvmtest.data

import com.example.mvvmtest.ui.modelo.Comidas

class FrutasDataSet {

    fun crearListaDeComidas(): List<Comidas> {
        return listOf(
            Comidas("Pollo Tapado", 60.5F, 250.3F, "Dulcete"),
            Comidas("Bistec", 60.4F, 280.2F, "Amargo"),
            Comidas("Carne Molida", 80.3F, 320.9F, "Dulcete Amargo")
        )
    }
}