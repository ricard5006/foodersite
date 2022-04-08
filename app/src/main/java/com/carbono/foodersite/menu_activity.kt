package com.carbono.foodersite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.carbono.foodersite.DbHelper.DbHelper
import com.carbono.foodersite.adapters.adapterMenu
import com.carbono.foodersite.adapters.adapterPedido
import com.carbono.foodersite.objetos.mesa
import com.carbono.foodersite.objetos.pedido
import com.carbono.foodersite.objetos.producto

class menu_activity : AppCompatActivity() {

    var idMesa:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val ab = supportActionBar
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true)
        }

        //captura la mesa qe selecciono
        var textViewNumMesa = findViewById<TextView>(R.id.textViewNumMesa)
        val bundl = intent.extras
        idMesa = bundl?.getInt("idmesa")
        textViewNumMesa.setText("Mesa #"+idMesa)

        textViewNumMesa.setOnClickListener { mesaDisponible() }

        listarMenu()

        listarPedido(idMesa)

    }

    private fun mesaDisponible() {
        try{

            val objDb = DbHelper(this)
            var objMesa = mesa()
            objMesa.id = idMesa
            objMesa.estado="disponible"
            objDb.updateMesa(objMesa)
            Toast.makeText(this,"mesa habilitada!",Toast.LENGTH_LONG).show()

        }catch (e:Exception){
            Toast.makeText(this,"Error [mesaDisponible]"+e.message,Toast.LENGTH_LONG).show()
        }

    }

    private fun mesaOcupada() {
        try{
        val objDb = DbHelper(this)
        var objMesa = mesa()
        objMesa.id = idMesa
        objMesa.estado="ocupada"
        objDb.updateMesa(objMesa)

    }catch (e:Exception){
        Toast.makeText(this,"Error [mesaOcupada]"+e.message,Toast.LENGTH_LONG).show()
    }

    }

    private fun listarMenu() {
        val objDb = DbHelper(this)
        var listProductos:List<producto> = ArrayList()

        val lvProductos = findViewById<ListView>(R.id.lvProductos)

        listProductos = objDb.selectAllProductos()

        val adapter = adapterMenu(this,listProductos)

        lvProductos.adapter = adapter

        lvProductos.onItemLongClickListener = AdapterView.OnItemLongClickListener{
            parent,view,position,id ->

            //cuando se selecciona del menu se pasa a la lista "pedido" de la mesa seleccionada
            var objProducto=producto()
            var objMesa=mesa()
            objProducto.id = listProductos.elementAt(position).id
            objMesa.id = idMesa
            generarPedido(objProducto,objMesa)

            true
        }



    }

    private fun generarPedido(objProducto: producto,objMesa:mesa) {
        var objDb = DbHelper(this)

        var objPedido = pedido()


        objPedido.Producto = objProducto
        objPedido.Mesa = objMesa
        objPedido.estado = "TRUE"

        objDb.insertPedido(objPedido)


        mesaOcupada()

        listarPedido(idMesa)

    }

    private fun listarPedido(idmesa:Int?) {

        //muestra los productos cargados en este pedido para esta mesa en cuestion
        val objDb = DbHelper(this)
        var listProdByPedido:List<pedido> = ArrayList()

        val lvPedido = findViewById<ListView>(R.id.lvPedido)

        listProdByPedido = objDb.selectProductoByPedido(idmesa)

        val adapter = adapterPedido(this,listProdByPedido)

        lvPedido.adapter = adapter


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}