package com.carbono.foodersite.objetos

class pedido {


    var id:String?=null
    var mesa:mesa?=null
    var producto:producto?=null

    constructor()

    constructor(id_:String,mesa_:mesa,producto_: producto){
        this.id=id_
        this.mesa=mesa_
        this.producto=producto_

    }

}