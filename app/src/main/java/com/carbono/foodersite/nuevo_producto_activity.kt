package com.carbono.foodersite

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.carbono.foodersite.DbHelper.DbHelper
import com.carbono.foodersite.adapters.adapterMenu
import com.carbono.foodersite.objetos.producto
import com.carbono.foodersite.utilidades.dialogArchivos
import java.io.File

class nuevo_producto_activity : AppCompatActivity() {
    var ruta:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)

        val imageProducto = findViewById<ImageView>(R.id.imageProductoResult)
        val btnAddProducto = findViewById<Button>(R.id.btnAddProducto)
        val textNombre = findViewById<EditText>(R.id.textNombre)
        val textDescripcion = findViewById<EditText>(R.id.textDescripcion)
        val textPrecio = findViewById<EditText>(R.id.textPrecio)



        val ab = supportActionBar
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true)
        }

        listarProductos()




        imageProducto.setOnClickListener {
            val i = Intent(this,dialogArchivos::class.java)
            startActivityForResult(i,5)


        }

        btnAddProducto.setOnClickListener {
            guardarNuevoProducto(textNombre.text.toString()
                ,textDescripcion.text.toString()
                ,textPrecio.text.toString()
                ,ruta)

            textNombre.setText("")
            textDescripcion.setText("")
            textPrecio.setText("")
            imageProducto.setImageResource(R.drawable.ic_baseline_restaurant_24_grey)
        }

    }



    private fun listarProductos() {
        try{

            val objDb = DbHelper(this)
            var listProductos:List<producto> = ArrayList()


            val listAllProductos = findViewById<ListView>(R.id.listAllProductos)
            listProductos = objDb.selectAllProductos()

            listAllProductos.adapter = adapterMenu(this,listProductos)

            listAllProductos.onItemLongClickListener = AdapterView.OnItemLongClickListener{
                parent,view,position,id ->

                val objProd = producto()
                objProd.id = listProductos.elementAt(position).id
                objProd.habilitado = "FALSE"
                objDb.updateProducto(objProd)

                listarProductos()

                true
            }



        }catch (e:Exception){Toast.makeText(this,"Error [listarProductos]: "+e.message,Toast.LENGTH_LONG).show()}


    }

    private fun guardarNuevoProducto(nombre: String, descripcion: String, precio: String,urlFoto:String?) {
    try{

    val objDb = DbHelper(this)
    val objProducto = producto()

    objProducto.nombre = nombre
    objProducto.descripcion = descripcion
    objProducto.precio = precio.toIntOrNull()
    objProducto.foto = urlFoto
    objProducto.habilitado = "TRUE"

        objDb.insertProducto(objProducto)
        listarProductos()

        Toast.makeText(this,"Producto Añadido!",Toast.LENGTH_LONG).show()


    }catch (e:Exception){
    Toast.makeText(this,"Error [guardarNuevoProducto] "+e.message,Toast.LENGTH_LONG).show()
    }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

       val imageProducto = findViewById<ImageView>(R.id.imageProductoResult)
        if(resultCode == RESULT_OK){
            if(requestCode == 5){

                ruta = data!!.getStringExtra("ruta")


                var imgFile = File(ruta)
                if(imgFile.exists()){
                    var mbitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                    if(mbitmap != null) {
                        imageProducto.setImageBitmap(mbitmap)
                    }
                }






            }
        }

    }




}