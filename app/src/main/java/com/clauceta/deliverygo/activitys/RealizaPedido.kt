package com.clauceta.deliverygo.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.config.ConfiguracaoFirebase
import com.clauceta.deliverygo.models.Pedido
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_realiza_pedido.*

class RealizaPedido : AppCompatActivity() {

    private var referenciafirebase = FirebaseDatabase.getInstance().getReference("Pedido")
    private var myAuth = FirebaseAuth.getInstance().currentUser!!.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realiza_pedido)


        botao_finalizar_pedido.setOnClickListener {

            val endereco_pedido_origem_rua: String = campo_endereco_origem.text.toString()
            val endereco_pedido_origem_tipo: String = campo_tipo_origem.text.toString()
            val endereco_pedido_destino_rua: String = campo_endereco_destino.text.toString()
            val descricao_pedido: String = campo_descricao_encomenda.text.toString()


            //Validaçao de campos
            if(endereco_pedido_origem_rua.isNullOrEmpty() || endereco_pedido_origem_tipo.isNullOrEmpty() || endereco_pedido_destino_rua.isNullOrEmpty() || descricao_pedido.isNullOrEmpty() ){
                //nao faz cadastro
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show()

            }else{
                //faz cadastro do pedido
                registraPedido( endereco_pedido_origem_rua, endereco_pedido_origem_tipo, endereco_pedido_destino_rua, descricao_pedido)
                finish()
            }


        }


    }


    fun registraPedido(origemrua: String, origemtipo: String, destinorua: String, pedidodescricao: String ){

        referenciafirebase = ConfiguracaoFirebase.getFirebasePedido()

        var pedido = Pedido()
        pedido.setIdUsuario(myAuth)
        pedido.setPedido_origem_rua(origemrua)
        pedido.setPedido_origem_tipo(origemtipo)
        pedido.setPedido_destino_rua(destinorua)
        pedido.setPedido_descricao(pedidodescricao)

        referenciafirebase.push().setValue(pedido)




    }
}
