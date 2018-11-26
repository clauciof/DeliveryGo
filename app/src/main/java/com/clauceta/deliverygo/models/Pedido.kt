package com.clauceta.deliverygo.models

open class Pedido {

    private var id: Int = 1
    private var endereco_pedido_origem_rua: String = "_endereco"
    private var endereco_pedido_origem_numero: String = "_endereco_numero"
    private var endereco_pedido_destino_rua: String = "_endereco"
    private var endereco_pedido_destino_numero: String = "_endereco_numero"
    private var descricao_pedido: String = "_descricaoo"

    open fun setId(numeroid: Int){
        this.id = numeroid
    }

    open fun setPedido_origem_rua(origemrua: String){
        this.endereco_pedido_origem_rua = origemrua
    }

    open fun setPedido_origem_numero(origemnumero: String){
       this.endereco_pedido_origem_numero = origemnumero
    }

    open fun setPedido_destino_rua(destinorua: String){
        this.endereco_pedido_destino_rua = destinorua
    }

    open fun setPedido_destino_numero(destinonumero: String){
        this.endereco_pedido_destino_numero = destinonumero
    }

    open fun setPedido_descricao(pedidodescricao: String){
        this.descricao_pedido = pedidodescricao
    }

    open fun getId(): Int{
        return this.id
    }

    open fun getPedido_origem_rua(): String{
        return this.endereco_pedido_origem_rua
    }

    open fun getPedido_origem_numero(): String{
        return this.endereco_pedido_origem_numero
    }

    open fun getPedido_destino_rua(): String{
        return this.endereco_pedido_destino_rua
    }

    open fun getPedido_destino_numero(): String{
        return this.endereco_pedido_destino_numero
    }

    open fun getPedido_descricao(): String{
        return this.descricao_pedido
    }


}