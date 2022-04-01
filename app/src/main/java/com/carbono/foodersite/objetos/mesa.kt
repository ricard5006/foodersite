package com.carbono.foodersite.objetos

class mesa {

    var id:Int?=null
    var nombre:String?=null
    var ubicacion:String?=null
    var estado:String?=null
    var producto:producto?=null

    constructor()

    constructor(id_:Int,nombre_:String,ubicacion_:String,estado_:String,producto_:producto){

        this.id=id_
        this.nombre=nombre_
        this.ubicacion=ubicacion_
        this.estado=estado_
        this.producto=producto_

    }
}