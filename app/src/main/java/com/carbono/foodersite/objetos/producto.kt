package com.carbono.foodersite.objetos

class producto {


    var id:String?=null
    var nombre:String?=null
    var descripcion:String?=null
    var foto:String?=null
    var precio:Int?=null
    var cantidad:Int?=null
    constructor()
    constructor(id_:String,nombre_:String,descripcion_:String,foto_:String,precio_:Int,cantidad_:Int){

        this.id=id_
        this.nombre=nombre_
        this.descripcion=descripcion_
        this.foto=foto_
        this.precio=precio_
        this.cantidad=cantidad_


    }

}