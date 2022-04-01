package com.carbono.foodersite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.*
import com.carbono.foodersite.DbHelper.DbHelper
import com.carbono.foodersite.adapters.adapterMesas
import com.carbono.foodersite.objetos.mesa

class mesas_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mesas_layout)



        listarMesas()




    }

    private fun listarMesas() {
        val objDb = DbHelper(this)
        var listMesas:List<mesa> = ArrayList()

        val grilla = findViewById<GridView>(R.id.grilla)

        listMesas = objDb.selectAllMesas()

        val adapter = adapterMesas(this,listMesas)
        grilla.adapter = adapter

        grilla.onItemLongClickListener = AdapterView.OnItemLongClickListener{
                parent,view,position,id ->

            var mesa = listMesas.elementAt(position).id
//idea: abrir un activity o fragment con la mesa y su pedido

            true
        }



    }

    private fun mesaNueva() {
        val objMesa = mesa()
        val objDb = DbHelper(this)

        //consultar ultima mesa
        var lastIDMesa = objDb.selectLastMesa().id

        if (lastIDMesa != null) {
            var mesaNueva = lastIDMesa+1

            objMesa.nombre = "Mesa #"+mesaNueva
            objMesa.estado = "disponible"  //estados= ocupado o disponible
            objDb.addMesa(objMesa)
        }

        listarMesas()

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId

        if(id==R.id.ItAdd){
            mesaNueva()
            return true
        }

        if(id==R.id.ItConfig){
            val i = Intent(this,configuracion_activity::class.java)
            startActivity(i)
            return true
        }

        if(id==R.id.ItSalir){
            finish()
        }

        return true
    }




}