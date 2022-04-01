package com.carbono.foodersite

import android.database.sqlite.SQLiteException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.carbono.foodersite.DbHelper.DbHelper
import com.carbono.foodersite.adapters.adapterUsuarios
import com.carbono.foodersite.objetos.usuario

class configuracion_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.configuracion_layout)

        val TbAddUsuario = findViewById<EditText>(R.id.TbAddUsuario)
        val TbAddPass = findViewById<EditText>(R.id.TbAddPass)
        val btnAddUsuario =  findViewById<Button>(R.id.btnAddUsuario)

        listarUsuarios()

        btnAddUsuario.setOnClickListener {
            addUsuario(TbAddUsuario.text.toString(), TbAddPass.text.toString())
            TbAddUsuario.setText("")
            TbAddPass.setText("")
        }




    }

    private fun addUsuario(usr: String,pass:String) {
    try{

        val objDb = DbHelper(this)
        val objUsr = usuario()

        objUsr.nombre = usr
        objUsr.contrasena = pass
        objDb.updateUsuarios(objUsr)


        listarUsuarios()

    }catch (e:SQLiteException){
        Toast.makeText(this,"Error [addUsuario] "+e,Toast.LENGTH_LONG).show()
    }


    }

    private fun listarUsuarios(){

        try {
            val objDb = DbHelper(this)
            var objUsr = usuario()
            val list = objDb.selectUsuarios()
            val adapter = adapterUsuarios(this, list)

            val listUsuarios = findViewById<ListView>(R.id.listUsuarios)

            listUsuarios.adapter = adapter

            listUsuarios.onItemLongClickListener =
                AdapterView.OnItemLongClickListener { parent, view, position, id ->

                    objUsr.id = list.elementAt(position).id
                    objUsr.nombre = list.elementAt(position).nombre

                    if(objDb.deleteUsuario(objUsr)==1) {
                        Toast.makeText(
                            this,
                            "usuario " + objUsr.nombre + " eliminado!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    listarUsuarios()

                    true
                }
        }catch (e:Exception){
            Toast.makeText(this,"Error [listarUsuarios] "+e,Toast.LENGTH_LONG).show()
        }

    }
}