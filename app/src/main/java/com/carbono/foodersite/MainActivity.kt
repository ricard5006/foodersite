package com.carbono.foodersite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

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
                    val i = Intent(this,configuracion_activity::class.java)
                    startActivity(i)
                }else{



                }

            }

        }


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