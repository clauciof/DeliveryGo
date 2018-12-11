package com.clauceta.deliverygo.activitys

import android.app.Activity
import android.content.Intent
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
import android.view.View
import android.widget.ImageView
import com.clauceta.deliverygo.BuildConfig
import com.clauceta.deliverygo.config.GlideApp
import kotlinx.android.synthetic.main.header.*



import java.io.File


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

   /* var database = FirebaseDatabase.getInstance()
    var DBreferencia_usuario = database.getReference("Usuario")
    var DBreferencia_pedido = database.getReference("Pedido")*/
    var firebaseauth = FirebaseAuth.getInstance().currentUser!!.uid
    var firebaseusuario = FirebaseAuth.getInstance()

    companion object {
        private const val REQUEST_CAMERA: Int = 1
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationview.setNavigationItemSelectedListener(this)
        val headView: View = navigationview.getHeaderView(0)
        val fotoPerfil: ImageView = headView.findViewById(R.id.foto_usuario)

        fotoPerfil.setOnClickListener {
            tiraFoto()
        }

        botao_realiza_pedido.setOnClickListener {
            val pagina_realiza_pedido = Intent(this, RealizaPedido::class.java)
            startActivity(pagina_realiza_pedido)
        }

        Toast.makeText(this, firebaseauth, Toast.LENGTH_SHORT).show()


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

            R.id.foto_usuario ->{
                tiraFoto()
            }

            R.id.seuspedidos ->{
                Toast.makeText(this, "seus pedidos", Toast.LENGTH_SHORT).show()
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

     fun onDrawerOpened (drawerView: View){
         foto_usuario

    }



    fun tiraFoto(){
       /* val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_CAMERA)
        }else{
            Toast.makeText(this, "Impossivel tirar foto", Toast.LENGTH_SHORT).show()
        }*/

        val tirarFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (tirarFoto.resolveActivity(packageManager) != null) {
            val arquivoFoto = montaArquivoFoto()
            val uriFoto = FileProvider.getUriForFile(this, "${BuildConfig.APPLICATION_ID}.fileprovider", arquivoFoto)
            tirarFoto.putExtra(MediaStore.EXTRA_OUTPUT, uriFoto)
            startActivityForResult(tirarFoto, REQUEST_CAMERA)
        } else {
            Toast.makeText(this, "Impossível tirar foto", Toast.LENGTH_SHORT).show()
        }

    }

    private fun montaArquivoFoto(): File {
        val nomeArquivo = System.currentTimeMillis().toString()
        val diretorioArquivo = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val arquivoFoto = File.createTempFile(nomeArquivo, "jpg", diretorioArquivo)

        caminhoFoto = arquivoFoto.absolutePath

        return arquivoFoto
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        //Recebendo o resultado do Request de Camera
        //exibe a foto retornada pelo aplicativo de câmera
        if(requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK){

            GlideApp.with(this)
                    .load()
                    .placeholder()
                    .centerCrop()
                    .into()

           // caminhoFotoAceita = caminhoFoto

        }

    }

}
