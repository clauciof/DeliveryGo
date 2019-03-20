package com.clauceta.deliverygo.activitys

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import android.widget.Toast
import com.clauceta.deliverygo.R
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.clauceta.deliverygo.BuildConfig
import com.clauceta.deliverygo.config.GlideApp
import com.clauceta.deliverygo.models.Pedido
import com.clauceta.deliverygo.models.Usuario
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.seus_pedidos_fragment.*


import java.io.File
import com.google.firebase.database.DataSnapshot
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    // retorna id unico do usuario logado
    var id_current_user = FirebaseAuth.getInstance().currentUser!!.uid

    // referencia para a classe usuario do usuario logado
    var usuarioatual = FirebaseDatabase.getInstance().reference.child("Usuario").child(id_current_user)

    //referencia da autenticação
    var firebaseusuario = FirebaseAuth.getInstance()


    // referencia o Storage ~do usuario logado~ do Firebase
    var fotoStorage = FirebaseStorage.getInstance().getReference("UsersPhotos").child(id_current_user)


    // referencia para a classe usuario do usuario logado
    var pedidosUsuario = FirebaseDatabase.getInstance().reference.child("Pedido")


    //Lista de Pedidos

    var pedidosLista: MutableList<Pedido> = mutableListOf()


    var caminhoFoto:String? = null //salva o caminho da foto tirada
    var caminhoFotoAceita:String? = null //salva o caminho da foto aceita pelo usuário

    companion object {
        private const val REQUEST_CAMERA: Int = 1
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carregaDados(this)

        navigationview.setNavigationItemSelectedListener(this)
        val headView: View = navigationview.getHeaderView(0)
        val fotoPerfil: ImageView = headView.findViewById(R.id.foto_usuario)


        var navigationView: NavigationView = findViewById(R.id.navigationview)

        var headerview: View = navigationView.getHeaderView(0)

        headerview.setOnClickListener {
           foto_usuario.setOnClickListener(){
               carregafoto()
           }
        }

        botao_realiza_pedido.setOnClickListener {
            val pagina_realiza_pedido = Intent(this, RealizaPedido::class.java)
            startActivity(pagina_realiza_pedido)
        }

        Toast.makeText(this, id_current_user, Toast.LENGTH_SHORT).show()

}


    override fun onNavigationItemSelected(item: MenuItem):Boolean {


        when(item.itemId){

            R.id.seuspedidos ->{
               // Toast.makeText(this, "seus pedidos", Toast.LENGTH_SHORT).show()

                val pagina_seus_pedidos = Intent(this, SeusPedidos::class.java)
                startActivity(pagina_seus_pedidos)
            }

            R.id.suasentregas ->{
                Toast.makeText(this, "suas entregas", Toast.LENGTH_SHORT).show()
            }

            R.id.pedidoemandamento ->{
                Toast.makeText(this, "pedidos em andamento", Toast.LENGTH_SHORT).show()
            }
            R.id.enderecos ->{
                Toast.makeText(this, "enderecos", Toast.LENGTH_SHORT).show()
            }

            R.id.editarperfil->{
                val pagina_edita_perfil = Intent(this, EditaPerfilActivity::class.java)
                startActivity(pagina_edita_perfil)
            }

            R.id.sair ->{
                Toast.makeText(this, "sair", Toast.LENGTH_SHORT).show()
                firebaseusuario.signOut()
                val pagina_login = Intent(this, Login::class.java)
                startActivity(pagina_login)
                finish()

            }

        }
         return true
    }

     fun carregafoto (){
         val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
         intent.setType("image/*")
         intent.setAction(Intent.ACTION_GET_CONTENT)
         startActivityForResult(intent, REQUEST_CAMERA)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Recebendo o resultado do Request de Camera
        //exibe a foto retornada pelo aplicativo de câmera
        if(requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK){

         var path: Uri

         path = data!!.data

         var ocaminhoFoto = fotoStorage

        caminhoFoto = path.toString()

        // updateFoto(caminhoFoto, path)

          GlideApp.with(this)
                    .load(caminhoFoto)
                    .placeholder(R.drawable.ic_usuario)
                    .centerCrop()
                    .apply(RequestOptions().circleCrop())
                    .into(foto_usuario)

           caminhoFotoAceita = caminhoFoto
        }
    }


    //exibe as informações dousuario na Activity
    private fun carregaDados(context: Context) {


        // Listener para retornar dados basicos do usuario da nuvem
        usuarioatual.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var usuario = Usuario()

                usuario.setnome(dataSnapshot.child("nome").value.toString())
                usuario.setemail(dataSnapshot.child("email").value.toString())


                nickname_usuario.text = usuario.getnome()
                email_usuario.text = usuario.getemail()

                GlideApp.with(context)
                        .load(caminhoFoto)
                        .placeholder(R.drawable.ic_usuario)
                        .centerCrop()
                        .apply(RequestOptions().circleCrop())
                        .into(foto_usuario)

                caminhoFotoAceita = caminhoFoto

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }


    fun updateFoto(caminhoFotoAceita: StorageReference, path: Uri){


        val caminhoFotoPerfil = caminhoFotoAceita

        caminhoFotoPerfil.delete().addOnSuccessListener {
            Toast.makeText(this,"deletou!",Toast.LENGTH_SHORT).show()
        }
        caminhoFotoAceita.putFile(path!!)

    }

}
