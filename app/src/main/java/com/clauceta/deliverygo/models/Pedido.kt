package com.clauceta.deliverygo.models

open class Pedido {

    private var id: String = "_id"
    private var endereco_pedido_origem_rua: String = "_endereco_origem"
    private var endereco_pedido_origem_tipo: String = "_tipo"
    private var endereco_pedido_destino_rua: String = "_endereco_destino"
    private var descricao_pedido: String = "_descricaoo"

    open fun setIdUsuario(codigoid: String){
        this.id = codigoid
    }

    open fun setPedido_origem_rua(origemrua: String){
        this.endereco_pedido_origem_rua = origemrua
    }

    open fun setPedido_origem_tipo(tipo: String){
        this.endereco_pedido_origem_tipo = tipo
    }

    open fun setPedido_destino_rua(destinorua: String){
        this.endereco_pedido_destino_rua = destinorua
    }


    open fun setPedido_descricao(pedidodescricao: String){
        this.descricao_pedido = pedidodescricao
    }

    open fun getId(): String{
        return this.id
    }

    open fun getPedido_origem_rua(): String{
        return this.endereco_pedido_origem_rua
    }

    open fun getPedido_origem_tipo(): String{
        return this.endereco_pedido_origem_tipo
    }

    open fun getPedido_destino_rua(): String{
        return this.endereco_pedido_destino_rua
    }


    open fun getPedido_descricao(): String{
        return this.descricao_pedido
    }


}