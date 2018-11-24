package com.clauceta.deliverygo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var database = FirebaseDatabase.getInstance()
    var DBreferencia_usuario = database.getReference("Usuario")
    var DBreferencia_pedido = database.getReference("Pedido")
    var firebaseauth = FirebaseAuth.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigationview.setNavigationItemSelectedListener(this)

        /*val usuario = Usuario()
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

        DBreferencia_pedido.child(pedido.getId().toString()).setValue(pedido)*/
    }

     override fun onNavigationItemSelected(item: MenuItem):Boolean {
        when(item.itemId){
            R.id.suasviagens->{
                Toast.makeText(this, "suas viagens", Toast.LENGTH_SHORT).show()
            }

            R.id.suasentregas->{
                Toast.makeText(this, "suas entregas", Toast.LENGTH_SHORT).show()
            }

            R.id.pedidoemandamento->{
                Toast.makeText(this, "pedidos em andamento", Toast.LENGTH_SHORT).show()
            }
            R.id.enderecos->{
                Toast.makeText(this, "enderecos", Toast.LENGTH_SHORT).show()
            }

            R.id.sair->{
                Toast.makeText(this, "sair", Toast.LENGTH_SHORT).show()
                firebaseauth.signOut()
                val pagina_login = Intent(this, Login::class.java)
                startActivity(pagina_login)
                finish()

            }

        }
         return true
    }


}
