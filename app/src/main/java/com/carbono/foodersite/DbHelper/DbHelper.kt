package com.carbono.foodersite.DbHelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.carbono.foodersite.objetos.*

class DbHelper(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER)
{
    companion object{
        private val DATABASE_NAME = "foodersite.db"
        private val DATABASE_VER = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE_PARAMETRO:String =
            ("CREATE TABLE parametros (parametro TEXT PRIMARY KEY, valor TEXT)")

        val CREATE_TABLE_USUARIO:String =
            ("CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, password TEXT)")

        val CREATE_TABLE_PRODUCTO:String =
            ("CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, descripcion TEXT, foto TEXT, precio INTEGER, cantidad INTEGER, habilitado TEXT DEFAULT 'TRUE')")

        val CREATE_TABLE_MESA:String =
            ("CREATE TABLE mesas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, ubicacion TEXT, estado TEXT, idProducto INTEGER)")

        val INSERT_MESA:String =
            ("INSERT INTO mesas (nombre,ubicacion,estado,idProducto)VALUES('Mesa #1','','disponible','')")

        val CREATE_TABLE_PEDIDO:String =
            ("CREATE TABLE pedidos (id INTEGER PRIMARY KEY AUTOINCREMENT, idMesa INTEGER, idProducto INTEGER, estado TEXT)")

        db!!.execSQL(CREATE_TABLE_PARAMETRO)
        db!!.execSQL(CREATE_TABLE_USUARIO)
        db!!.execSQL(CREATE_TABLE_PRODUCTO)
        db!!.execSQL(CREATE_TABLE_MESA)
        db!!.execSQL(INSERT_MESA)
        db!!.execSQL(CREATE_TABLE_PEDIDO)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS parametros")
        onCreate(db!!)
        db!!.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db!!)
        //db!!.execSQL("DROP TABLE IF EXISTS productos")
        //onCreate(db!!)
        db!!.execSQL("DROP TABLE IF EXISTS mesas")
        onCreate(db!!)
        db!!.execSQL("DROP TABLE IF EXISTS pedidos")
        onCreate(db!!)

    }

    fun updateParametros(param: parametro): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("parametro", param.parametro)
        values.put("valor", param.valor)
        return db.replace("parametros", null, values)

    }


    @SuppressLint("Range")
    fun selectParametros(objParam: parametro):String {
        var dato:String=""
        val selectQuery = "SELECT * FROM parametros WHERE parametro = ?"
        val db = this.writableDatabase
        val cursor : Cursor
        cursor = db.rawQuery(selectQuery, arrayOf(objParam.parametro.toString()))
        if (cursor.moveToFirst()) {

                do {
                    dato = cursor.getString(cursor.getColumnIndex("valor"))

                } while (cursor.moveToNext())

        }
       // db.close()
        return dato



    }


    fun updateUsuarios(usr: usuario):Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("usuario", usr.nombre)
        values.put("password", usr.contrasena)
        return db.replace("usuarios", null, values)


    }

    @SuppressLint("Range")
    fun selectUsuarioBy(objUsuario: usuario):String {
        var dato:String=""
        val selectQuery = "SELECT * FROM usuarios WHERE usuario = ?"
        val db = this.writableDatabase

        val cursor = db.rawQuery(selectQuery, arrayOf(objUsuario.nombre.toString()))
        if (cursor.moveToFirst()) {

            do {
                dato = cursor.getString(cursor.getColumnIndex("password"))

            } while (cursor.moveToNext())

        }
        db.close()
        return dato

    }

    @SuppressLint("Range")
    fun selectUsuarios():List<usuario> {

       val listUsuarios = ArrayList<usuario>()
        val selectQuery = "SELECT * FROM usuarios"
        val db = this.writableDatabase

        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {

            do {
                val usr = usuario()
                usr.id = cursor.getString(cursor.getColumnIndex("id"))
                usr.nombre = cursor.getString(cursor.getColumnIndex("usuario"))
                usr.contrasena = cursor.getString(cursor.getColumnIndex("password"))


                listUsuarios.add(usr)
            } while (cursor.moveToNext())

        }
        db.close()
        return listUsuarios

    }

    fun deleteUsuario(usr:usuario):Int {

        val db = this.writableDatabase
        val resultado = db.delete("usuarios","id = ?", arrayOf(usr.id))
        db.close()

        return resultado
    }

    fun addMesa(mesa:mesa):Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nombre", mesa.nombre)
        values.put("ubicacion", mesa.ubicacion)
        values.put("estado", mesa.estado)

        return db.replace("mesas", null, values)
    }

    @SuppressLint("Range")
    fun selectLastMesa(): mesa {

        val selectQuery = "SELECT * FROM mesas ORDER BY id DESC LIMIT 1"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        val m = mesa()

        if (cursor.moveToFirst()) {

            do{
                m.id = cursor.getInt(cursor.getColumnIndex("id"))
            } while (cursor.moveToNext())
        }
        db.close()
    return m
    }

    @SuppressLint("Range")
    fun selectAllMesas(): List<mesa> {
        val listMesas = ArrayList<mesa>()

        val selectQuery="SELECT * FROM mesas"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)

        if(cursor.moveToFirst()){
            do{
                val mesa = mesa()
                mesa.id = cursor.getInt(cursor.getColumnIndex("id"))
                mesa.nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                mesa.ubicacion = cursor.getString(cursor.getColumnIndex("ubicacion"))
                mesa.estado = cursor.getString(cursor.getColumnIndex("estado"))
                listMesas.add(mesa)

            }while (cursor.moveToNext())

        }
        db.close()
        return listMesas
    }
    @SuppressLint("Range")
    fun selectAllProductos(): List<producto> {
        val listProductos = ArrayList<producto>()
        val selectQuery="SELECT * FROM productos where habilitado='TRUE'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)

        if(cursor.moveToFirst()){
            do{
                val prod = producto()
                prod.id = cursor.getInt(cursor.getColumnIndex("id"))
                prod.nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                prod.descripcion = cursor.getString(cursor.getColumnIndex("descripcion"))
                prod.foto = cursor.getString(cursor.getColumnIndex("foto"))
                prod.precio = cursor.getInt(cursor.getColumnIndex("precio"))
                prod.habilitado = cursor.getString(cursor.getColumnIndex("habilitado"))

                listProductos.add(prod)

            }while (cursor.moveToNext())

        }
        db.close()

        return listProductos
    }

    fun insertProducto(objP: producto):Long {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nombre", objP.nombre)
        values.put("descripcion", objP.descripcion)
        values.put("foto", objP.foto)
        values.put("precio", objP.precio)
        values.put("habilitado", objP.habilitado)


        return db.replace("productos", null, values)

    }

    fun updateProducto(prod: producto) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put("habilitado",prod.habilitado)


        db.update("productos",values,"id=?" ,arrayOf(prod.id.toString()))

    }

    fun insertPedido(objPedido: pedido):Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("idMesa",objPedido.Mesa?.id.toString())
        values.put("idProducto",objPedido.Producto?.id.toString())
        values.put("estado",objPedido.estado.toString())

        return db.replace("pedidos", null, values)
    }

    @SuppressLint("Range")
    fun selectProductoByPedido(idMesa:Int?): List<pedido> {
        val listPedido = ArrayList<pedido>()

        val selectQuery="select * from pedidos " +
                "inner join productos on productos.id=pedidos.idProducto " +
                "where pedidos.idMesa = ?" +
                "and pedidos.estado = 'TRUE' "

        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,arrayOf(idMesa.toString()))

        if(cursor.moveToFirst()) {
            do {
                val pedido = pedido()
                val producto = producto()

                pedido.id = cursor.getString(cursor.getColumnIndex("id"))

                producto.nombre = cursor.getString(cursor.getColumnIndex("nombre"))
                producto.precio = cursor.getInt(cursor.getColumnIndex("precio"))

                pedido.Producto = producto

                listPedido.add(pedido)
                }while (cursor.moveToNext())

            }

        return listPedido
    }

    fun updateMesa(objMesa: mesa) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put("estado",objMesa.estado)


        db.update("mesas",values,"id=?" ,arrayOf(objMesa.id.toString()))
    }

}