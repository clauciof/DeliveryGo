package com.clauceta.deliverygo.activitys

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.request.RequestOptions
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.config.GlideApp
import com.clauceta.deliverygo.models.Pedido
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.edita_perfil.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.seus_pedidos_fragment.*
import java.net.URL

class EditaPerfilActivity: AppCompatActivity() {

    // retorna id unico do usuario logado
    var id_current_user = FirebaseAuth.getInstance().currentUser!!.uid

    // referencia o Storage ~do usuario logado~ do Firebase
    var fotoStorage = FirebaseStorage.getInstance().getReference("UsersPhotos").child(id_current_user)

    // referencia para a classe usuario do usuario logado
    var usuarioatual = FirebaseDatabase.getInstance().reference.child("Usuario").child(id_current_user)

    var ofotoStorage = FirebaseStorage.getInstance().getReference("UsersPhotos").child(id_current_user).child("fotoPerfil.jpeg")

    var urldafoto: String? = null


    var caminhoFoto:String? = null //salva o caminho da foto tirada
    var path: Uri? = null


    companion object {
        private const val REQUEST_CAMERA: Int = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.edita_perfil)

        carregaDados(this)


        icone_mudar_foto_perfil.setOnClickListener{
            carregaFoto()
        }


        botao_salvar_editar_perfil.setOnClickListener {

            val caminhoFotoAceita: StorageReference //salva o caminho da foto aceita pelo usuário e joga no backend

            //verificar se ja existe uma imagem e fazer update

            caminhoFotoAceita = fotoStorage.child("fotoPerfil")

            updateFoto(caminhoFotoAceita)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Recebendo o resultado do Request de Camera
        //exibe a foto retornada pelo aplicativo de câmera
        if(requestCode == EditaPerfilActivity.REQUEST_CAMERA && resultCode == Activity.RESULT_OK){


            path = data!!.data

            caminhoFoto = path.toString()

            GlideApp.with(this)
                    .load(caminhoFoto)
                    .placeholder(R.drawable.ic_usuario)
                    .centerCrop()
                    .apply(RequestOptions().circleCrop())
                    .into(icone_mudar_foto_perfil)
        }

    }


    fun carregaFoto(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, EditaPerfilActivity.REQUEST_CAMERA)
    }


    fun updateFoto(caminhoFotoAceita: StorageReference){


        val caminhoFotoPerfil = caminhoFotoAceita

        caminhoFotoPerfil.delete().addOnSuccessListener {
            Toast.makeText(this,"deletou!",Toast.LENGTH_SHORT).show()
        }
        caminhoFotoAceita.putFile(path!!).addOnSuccessListener {
            val result = it.metadata!!.reference!!.downloadUrl

            result.addOnSuccessListener {
                val urlFotoPerfil = it.toString()

                usuarioatual.child("urlfotoperfil").setValue(urlFotoPerfil)


            }
        }

    }

    private fun carregaDados(context: Context) {




       // Listener para retornar dados basicos do usuario da nuvem
        usuarioatual.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

             urldafoto = dataSnapshot.child("urlfotoperfil").value.toString()

                GlideApp.with(context)
                        .load(urldafoto)
                        .placeholder(R.drawable.ic_usuario)
                        .centerCrop()
                        .apply(RequestOptions().circleCrop())
                        .into(icone_mudar_foto_perfil)


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }



}