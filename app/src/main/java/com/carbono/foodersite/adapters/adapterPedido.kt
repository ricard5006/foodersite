package com.carbono.foodersite.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.carbono.foodersite.R
import com.carbono.foodersite.objetos.pedido
import com.carbono.foodersite.objetos.producto

class adapterPedido (private val mContext: Context, private val lista:List<pedido>):
    ArrayAdapter<pedido>(mContext,0,lista) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.list_pedidos_adapter,parent,false)

        val pedido = lista[position]

        var textNombre = layout.findViewById<TextView>(R.id.textNombreAdapter)
        var textPrecio = layout.findViewById<TextView>(R.id.textPrecioAdapter)

        textNombre.setText(pedido.Producto?.nombre.toString())
        textPrecio.setText(pedido.Producto?.precio.toString())

        return  layout
    }


}