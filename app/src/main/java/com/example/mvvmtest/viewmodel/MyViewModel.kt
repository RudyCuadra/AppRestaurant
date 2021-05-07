package com.example.mvvmtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtest.domain.FrutasUseCase
import com.example.mvvmtest.ui.modelo.Comidas

class MyViewModel: ViewModel() {

    val comidasUseCase = FrutasUseCase()
    private val listData = MutableLiveData<List<Comidas>>()

    init {
        getListaComidas()
    }

    fun setListaComida(listaFrutas:List<Comidas>){
        listData.value = listaFrutas
    }

    fun getListaComidas(){ //Proceso final del recorrido para pedir los datos a la api
        setListaComida(comidasUseCase.obtenerListaDeComidas())
    }

    //LIVE DATA escucha por el valor del mutable si cambio o no, SI cambio va a hacer algo, sino cambio se va a aquedar ahi esperando
    fun getListaComidasLiveData(): LiveData<List<Comidas>>{
        return listData
    }
}