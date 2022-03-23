package com.carbono.foodersite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.carbono.foodersite.objetos.mesa

class adapterMesas (private val mContext:Context,private val lista:List<mesa>):
        ArrayAdapter<mesa>(mContext,0,lista){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.list_mesas_adapter,parent,false)


        return layout
    }


}