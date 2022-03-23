package com.carbono.foodersite.objetos

class usuario {

    var id:String?=null
    var nombre:String?=null
    var contrasena:String?=null

    constructor()

    constructor(id_:String,nombre_:String,contrasena_:String){

        this.id=id_
        this.nombre=nombre_
        this.contrasena=contrasena_

    }
}