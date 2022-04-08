package com.carbono.foodersite.utilidades

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.AdapterView
import android.widget.GridView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.carbono.foodersite.R
import com.carbono.foodersite.adapters.adapterImagenes
import com.carbono.foodersite.nuevo_producto_activity
import com.carbono.foodersite.objetos.archivos
import java.io.File

class dialogArchivos : AppCompatActivity() {

    var lsRutas = ArrayList<String> ()
    var item= ArrayList<String> ()
    //var listArchivos = ArrayList<archivos>()
    //var listArchivos:ArrayList<archivos> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_archivos)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Verifica permisos para Android 6.0+
            checkExternalStoragePermission()
        }



        cargarLista("")

    }



    fun cargarLista(rutaAnterior: String) {
    //var objArchivos = archivos()


        if (rutaAnterior !== "") {
            lsRutas.add(rutaAnterior)
        }
        item = java.util.ArrayList()

        var ruta = "/"
        for (i in lsRutas.indices) {
            ruta += lsRutas[i]
        }
        val f = File(Environment.getExternalStoragePublicDirectory("/"), ruta)
        //File f = new File(Environment.getExternalStorageDirectory() + "/WN Finances/");
        val files = f.listFiles()
        item.add("./")
        //objArchivos.nombre="./"
        //objArchivos.rutaAbsoluta = "./"
        //listArchivos.add(objArchivos)
        try {
            for (i in files.indices) {
                val file = files[i]
                //var objArc = archivos()
                if (file.isDirectory) {
                    item.add(file.name + "/")
                    //objArc.nombre=file.name.toString() + "/"
                    //objArc.rutaAbsoluta = file.absolutePath.toString()
                    //listArchivos.add(objArc)

                }
                else
                {
                    if (file.name.contains(".jpg")) {
                       item.add(file.absolutePath)
                        //objArc.nombre = file.name.toString()
                        //objArc.rutaAbsoluta = file.absolutePath.toString()

                        //---------



                    }
                }


            }
        } catch (ex: Exception) {
            val z = ""
        }
        // Localizamos y llenamos la lista

        var grillaImagenes = findViewById<GridView>(R.id.grillaImagenes)
        
        grillaImagenes.adapter = adapterImagenes(this,item)


        grillaImagenes.onItemClickListener = AdapterView.OnItemClickListener {
                a, v, position, id ->

            if (position == 0) {
                if (lsRutas.size > 0) {
                    lsRutas.removeAt(lsRutas.size - 1)
                    cargarLista("")
                } else {
                    val i = Intent()
                    i.putExtra("ruta","")
                    setResult(RESULT_OK,i)
                    finish()

                }
            }else {
                if (item[position].contains(".jpg")) {
                    retornaRuta(position)

                } else {
                    cargarLista(item[position])

                }
            }

        }


    }

    private fun retornaRuta(position: Int) {
        var ruta = "/"

        for (i in lsRutas.indices) {
            ruta += lsRutas[i]
        }

        if(position > -1){

            val i = Intent(this, nuevo_producto_activity::class.java)

            i.putExtra("ruta", item.get(position))
            setResult(RESULT_OK, i)
            finish()



        }
    }

    private fun checkExternalStoragePermission() {
        val permissionCheck = ContextCompat.checkSelfPermission(
            this, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.")
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                225
            )
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer!")

        }
    }

}