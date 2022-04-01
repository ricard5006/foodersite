package com.carbono.foodersite.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.carbono.foodersite.R
import com.carbono.foodersite.objetos.mesa

class adapterMesas (private val mContext: Context, private val lista:List<mesa>):
    ArrayAdapter<mesa>(mContext,0,lista){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val layout = LayoutInflater.from(mContext).inflate(R.layout.list_mesas_adapter,parent,false)

        val mesa = lista[position]
        val idmesa = layout.findViewById<TextView>(R.id.idmesa)
        val ivcheck = layout.findViewById<ImageView>(R.id.ivcheck)

        idmesa.setText(mesa.nombre)
        if(mesa.estado.toString() != "disponible"){
            ivcheck.setImageResource(R.drawable.ic_baseline_check_box_24)
        }

        return layout
    }


}