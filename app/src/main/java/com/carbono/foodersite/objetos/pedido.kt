package com.carbono.foodersite.objetos

class pedido {


    var id:String?=null
    var Mesa:mesa?=null
    var Producto:producto?=null
    var estado:String?=null

    constructor()

    constructor(id_:String,Mesa_:mesa,Producto_: producto,estado_:String){
        this.id=id_
        this.Mesa=Mesa_
        this.Producto=Producto_
        this.estado = estado_

    }

}