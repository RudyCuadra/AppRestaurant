package com.example.mvvmtest.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.model.Comida

class FoodAdapter(internal var context: Context, internal var listOfFood: List<Comida>):
    RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtnamev.text = listOfFood.get(position).name
        holder.txtdescv.text = listOfFood.get(position).descripcion+"..."
        holder.txtPricev.text = listOfFood.get(position).precio.toString()
        holder.estrellas.rating = listOfFood.get(position).estrellas

    }

    override fun getItemCount(): Int {
        return listOfFood.size
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{

        var txtnamev = itemView.findViewById(R.id.txtNombre) as TextView
        var txtdescv = itemView.findViewById(R.id.idDesc) as TextView
        var txtPricev = itemView.findViewById(R.id.idPrecio) as TextView
        var estrellas = itemView.findViewById(R.id.Rating) as RatingBar

        init {
            txtnamev = itemView.findViewById(R.id.txtNombre) as TextView
            txtdescv = itemView.findViewById(R.id.idDesc) as TextView
            txtPricev = itemView.findViewById(R.id.idPrecio) as TextView
            estrellas = itemView.findViewById(R.id.Rating) as RatingBar
        }


        override fun onClick(p0: View?) {
            //iRecyclerClick.onClick(p0!!,adapterPosition)
        }

    }

}