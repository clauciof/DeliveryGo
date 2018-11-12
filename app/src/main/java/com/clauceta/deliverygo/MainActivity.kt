package com.clauceta.deliverygo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()
    var DBreferencia_usuario = database.getReference("Usuario")
    var DBreferencia_pedido = database.getReference("Pedido")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuario = Usuario()
        val pedido = Pedido()


        usuario.setnome("Claucio Filho")
        usuario.setemail("claucio@gmail.com")
        usuario.setid(0)
        usuario.setsenha("tsaftsfatsa")

        pedido.setPedido_origem_rua("Rua Sao Joaquim")
        pedido.setPedido_origem_numero("1731 ap 11")
        pedido.setPedido_destino_rua("AV. Carlos Botelho")
        pedido.setPedido_destino_numero("4040")
        pedido.setPedido_descricao("sanduiche")


        DBreferencia_usuario.child(usuario.getid().toString()).setValue(usuario)

        DBreferencia_pedido.child(pedido.getId().toString()).setValue(pedido)
    }


}
