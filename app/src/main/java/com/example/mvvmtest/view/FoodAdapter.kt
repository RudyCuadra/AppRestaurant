package com.example.mvvmtest.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.model.Comida
import com.example.mvvmtest.view.interfaces.IRecyclerClick

class FoodAdapter(internal var context: Context, internal var listOfFood: List<Comida>):
    RecyclerView.Adapter<FoodAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtidf.text = listOfFood.get(position).id.toString()
        holder.txtnamev.text = listOfFood.get(position).name
        holder.txtdescv.text = listOfFood.get(position).descripcion+"..."
        holder.txtPricev.text = listOfFood.get(position).precio.toString()
        holder.estrellas.rating = listOfFood.get(position).estrellas
        holder.imag.setImageURI(Uri.parse(listOfFood.get(position).imagen))

        holder.setClick(object:IRecyclerClick{
            override fun onClick(view: View, position: Int) {
               //NOTHING
            }
        })

        holder.itemView.setOnLongClickListener {
            Toast.makeText(context,"Click largo", Toast.LENGTH_LONG).show()
            false
        }



        holder.EditF.setOnClickListener {
            //Toast.makeText(context,"Click en EDITAR", Toast.LENGTH_SHORT).show()
            val intent = Intent(context,Contentfood::class.java)
            intent.putExtra("Activity","UPDATE")
            intent.putExtra("nombre",listOfFood[position].name)
            intent.putExtra("Descripcion",listOfFood[position].descripcion)
            intent.putExtra("Precio",listOfFood[position].precio)
            intent.putExtra("cantEst",listOfFood[position].estrellas)
            intent.putExtra("id",listOfFood[position].id.toString())
            intent.putExtra("imagenurl",listOfFood[position].imagen)
            context.startActivity(intent)
        }

        holder.vMas.setOnClickListener{
            val intent = Intent(context,Moreinfo::class.java)
            intent.putExtra("nombre",listOfFood[position].name)
            intent.putExtra("Descripcion",listOfFood[position].descripcion)
            intent.putExtra("Precio",listOfFood[position].precio)
            intent.putExtra("cantEst",listOfFood[position].estrellas)
            intent.putExtra("id",listOfFood[position].id.toString())
            intent.putExtra("imagenurl",listOfFood[position].imagen)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listOfFood.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var txtidf = itemView.findViewById(R.id.txtID) as TextView
        var txtnamev = itemView.findViewById(R.id.txtNombre) as TextView
        var txtdescv = itemView.findViewById(R.id.idDesc) as TextView
        var txtPricev = itemView.findViewById(R.id.idPrecio) as TextView
        var estrellas = itemView.findViewById(R.id.Rating) as RatingBar
        var imag = itemView.findViewById(R.id.imgFood) as AppCompatImageView
        var vMas = itemView.findViewById(R.id.LlVermas) as LinearLayout
        var EditF = itemView.findViewById(R.id.LlVerma) as LinearLayout

        lateinit var iRecyclerClick: IRecyclerClick

        init {
            txtidf = itemView.findViewById(R.id.txtID) as TextView
            txtnamev = itemView.findViewById(R.id.txtNombre) as TextView
            txtdescv = itemView.findViewById(R.id.idDesc) as TextView
            txtPricev = itemView.findViewById(R.id.idPrecio) as TextView
            estrellas = itemView.findViewById(R.id.Rating) as RatingBar
            imag = itemView.findViewById(R.id.imgFood) as AppCompatImageView
            vMas = itemView.findViewById(R.id.LlVermas) as LinearLayout
            EditF = itemView.findViewById(R.id.LlVerma) as LinearLayout
            itemView.setOnClickListener(this)
        }


        override fun onClick(p0: View?) {
           //if(adapterPosition!=0){
               iRecyclerClick.onClick(p0!!, adapterPosition)
           //}
        }

        fun setClick(iRecyclerClick: IRecyclerClick){
            this.iRecyclerClick = iRecyclerClick
        }

    }

}