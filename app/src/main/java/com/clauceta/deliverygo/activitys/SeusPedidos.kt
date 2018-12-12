package com.clauceta.deliverygo.activitys

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bumptech.glide.request.RequestOptions
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.config.GlideApp
import com.clauceta.deliverygo.models.Pedido
import com.clauceta.deliverygo.models.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.seus_pedidos_fragment.*

class SeusPedidos : AppCompatActivity() {

    var firebaseauth = FirebaseAuth.getInstance().currentUser!!.uid // retorna id unico do usuario logado

    var pedidosUsuario = FirebaseDatabase.getInstance().reference.child("Pedido")  // referencia para a classe usuario do usuario logado

    val pedidosLista: MutableList<Pedido> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.seus_pedidos_fragment)
        carregaDados(this)
    }


    private fun carregaDados(context: Context) {
        var countDataChange: Int = 0

        // Listener para retornar pedidos do usuario da nuvem
        pedidosUsuario.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
               // countDataChange++


                var count: Int = 0

               // var filho =  dataSnapshot.children
                //var query: Query

                //passando por todos os pedidos e adicionando cada pedido na lista
               /* dataSnapshot.children.forEach {

                    }*/

                var contaMatch: Int = 0
                dataSnapshot.children.forEach(){

                    var pedido = Pedido()

                       // Toast.makeText(context, it.child("pedido_descricao").value.toString(),Toast.LENGTH_SHORT).show()

                    if(it.child("id").value == firebaseauth) {

                        pedido.setPedido_descricao(it.child("pedido_descricao").value.toString())
                        pedido.setPedido_origem_rua(it.child("pedido_origem_rua").value.toString())
                        pedido.setPedido_destino_rua(it.child("pedido_destino_rua").value.toString())
                        pedidosLista.add(count,pedido)
                        count++

                    }


                    //val Descricaoitem1 = pedidosLista[0].getPedido_descricao()
                    //Toast.makeText(context, pedidosLista[count].getPedido_descricao(),Toast.LENGTH_SHORT).show()
                    //Toast.makeText(context,pedidosLista[count].getPedido_descricao(), Toast.LENGTH_SHORT).show()

                }
                //Toast.makeText(context, contaMatch.toString(), Toast.LENGTH_SHORT).show()
               // Toast.makeText(context, count.toString(),Toast.LENGTH_SHORT).show()
              /*  while (count>=0){
                    Toast.makeText(context,pedidosLista[count].getPedido_descricao(), Toast.LENGTH_SHORT).show()

                    count = count - 1
                }*/




               /* val Descricaoitem1 = pedidosLista[0].getPedido_descricao()
                val Descricaoitem2 = pedidosLista[1].getPedido_descricao() */


               // Toast.makeText(context, "Descricaoitem1"+Descricaoitem1,Toast.LENGTH_SHORT).show()
               // Toast.makeText(context,"Descricaoitem2"+Descricaoitem2,Toast.LENGTH_SHORT).show()
                val adapter = PedidosAdapter(context, pedidosLista)
                rvPedidos.adapter = adapter
                rvPedidos.layoutManager = LinearLayoutManager(context)

                //Toast.makeText(context, countDataChange.toString(),Toast.LENGTH_SHORT).show()

               //Toast.makeText(context,count.toString(), Toast.LENGTH_SHORT).show()
               //Toast.makeText(context,firebaseauth, Toast.LENGTH_SHORT).show()

            }
            override fun onCancelled(error: DatabaseError) {

            }



        })


    }
}
