package com.carbono.foodersite.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.carbono.foodersite.R


import com.carbono.foodersite.objetos.usuario
import java.lang.Exception


class adapterUsuarios (private val mContext: Context, private val lista:List<usuario>):
    ArrayAdapter<usuario>(mContext,0,lista){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.list_usuario_adapter,parent,false)

        val usr = lista[position]

        val tbUsuario = layout.findViewById<TextView>(R.id.tbUsuario)
        val tbPass = layout.findViewById<TextView>(R.id.tbPass)

    tbUsuario.setText(usr.nombre.toString())
    tbPass.setText(usr.contrasena?.substring(0,2)+"***")





        return layout
    }


}