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
import com.carbono.foodersite.objetos.archivos
import java.io.File

class adapterImagenes (private val mContext:Context,private val lista:List<String>):
    ArrayAdapter<String>(mContext,0,lista) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.list_imagenes_adapter,parent,false)

        var imagenArchivo = layout.findViewById<ImageView>(R.id.imagenArchivo)
        var textNombre = layout.findViewById<TextView>(R.id.textNombre)


        val img = lista[position]

        var imgFile = File(img)
        //var imgFile = File(img.rutaAbsoluta.toString())

        var mbitmap = BitmapFactory.decodeFile(imgFile.absolutePath)


        textNombre.setText(img.toString())
        //textNombre.setText(img.nombre)
        if(mbitmap != null) {
            imagenArchivo.setImageBitmap(mbitmap)
        }

        return layout
    }


}