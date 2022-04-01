package com.carbono.foodersite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.carbono.foodersite.DbHelper.DbHelper
import com.carbono.foodersite.adapters.adapterMenu
import com.carbono.foodersite.objetos.producto

class menu_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val ab = supportActionBar
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true)
        }

        listarMenu()

    }

    private fun listarMenu() {
        val objDb = DbHelper(this)
        var listProductos:List<producto> = ArrayList()

        val lvProductos = findViewById<ListView>(R.id.lvProductos)

        listProductos = objDb.selectAllProductos()

        val adapter = adapterMenu(this,listProductos)

        lvProductos.adapter = adapter



    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}