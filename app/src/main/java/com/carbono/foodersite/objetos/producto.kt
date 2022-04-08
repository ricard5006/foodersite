package com.carbono.foodersite.objetos

class producto {


    var id:Int?=null
    var nombre:String?=null
    var descripcion:String?=null
    var foto:String?=null
    var precio:Int?=null
    var cantidad:Int?=null
    var habilitado:String?=null

    constructor()
    constructor(id_:Int,nombre_:String,descripcion_:String,foto_:String,precio_:Int,cantidad_:Int,habilitado_:String){

        this.id=id_
        this.nombre=nombre_
        this.descripcion=descripcion_
        this.foto=foto_
        this.precio=precio_
        this.cantidad=cantidad_
        this.habilitado = habilitado_


    }

}