package com.carbono.foodersite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.carbono.foodersite.DbHelper.DbHelper
import com.carbono.foodersite.objetos.usuario

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tbUsuario = findViewById<EditText>(R.id.usuario)
        val tbPassword = findViewById<EditText>(R.id.password)
        val btnAceptar = findViewById<Button>(R.id.continuar)


        btnAceptar.setOnClickListener {
            if(textoVacio(tbUsuario) && textoVacio(tbPassword)){

                if(tbUsuario.text.toString() == "admin" && tbPassword.text.toString() == "admin"){
                    val i = Intent(this,mesas_activity::class.java)
                    startActivity(i)
                }
                if(validarUsuario(tbUsuario.text.toString(),tbPassword.text.toString())){
                    val i = Intent(this,mesas_activity::class.java)
                    startActivity(i)
                }

            }

        }


    }

    private fun validarUsuario(usr: String, pswd: String): Boolean {
        var respuesta=false
        val objDb = DbHelper(this)
        val objUsuario = usuario()
        objUsuario.nombre = usr
        objUsuario.contrasena = pswd

        if(objDb.selectUsuarioBy(objUsuario) == pswd){
            respuesta = true
        }


        return respuesta
    }


    fun textoVacio(txt: EditText): Boolean {
        if (txt.text.toString().trim { it <= ' ' } == "") {
            txt.error = "Campo obligatorio"
            return false
        } else {
            txt.error = null
        }
        return true
    }

}