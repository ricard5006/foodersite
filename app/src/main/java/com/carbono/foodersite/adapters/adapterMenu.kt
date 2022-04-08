package com.carbono.foodersite.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.carbono.foodersite.R
import com.carbono.foodersite.objetos.producto
import java.io.File

class adapterMenu (private val mContext:Context,private val lista:List<producto>):
    ArrayAdapter<producto>(mContext,0,lista){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.list_productos_adapter,parent,false)


        var imageProducto = layout.findViewById<ImageView>(R.id.imageProducto)
        var textNombre = layout.findViewById<TextView>(R.id.textNombre)
        var textDescripcion = layout.findViewById<TextView>(R.id.textDescripcion)
        var textPrecio = layout.findViewById<TextView>(R.id.textPrecio)

        val producto = lista[position]

        textNombre.setText(producto.nombre)
        textDescripcion.setText(producto.descripcion)
        textPrecio.setText(producto.precio.toString())


        var imgFile = File(producto.foto)
        if(imgFile.exists()){
            var mbitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            if(mbitmap != null) {
                imageProducto.setImageBitmap(mbitmap)
            }
        }


        return layout
    }


}