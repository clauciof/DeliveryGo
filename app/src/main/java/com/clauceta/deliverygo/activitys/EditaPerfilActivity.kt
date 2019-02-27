package com.clauceta.deliverygo.activitys

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.request.RequestOptions
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.config.GlideApp
import kotlinx.android.synthetic.main.edita_perfil.*
import kotlinx.android.synthetic.main.header.*

class EditaPerfilActivity: AppCompatActivity() {


    var caminhoFoto:String? = null //salva o caminho da foto tirada
    var caminhoFotoAceita:String? = null //salva o caminho da foto aceita pelo usuário

    companion object {
        private const val REQUEST_CAMERA: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.edita_perfil)

        icone_mudar_foto_perfil.setOnClickListener{
            carregaFoto()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Recebendo o resultado do Request de Camera
        //exibe a foto retornada pelo aplicativo de câmera
        if(requestCode == EditaPerfilActivity.REQUEST_CAMERA && resultCode == Activity.RESULT_OK){

            var path: Uri

            path = data!!.data

            caminhoFoto = path.toString()

            GlideApp.with(this)
                    .load(caminhoFoto)
                    .placeholder(R.drawable.ic_usuario)
                    .centerCrop()
                    .apply(RequestOptions().circleCrop())
                    .into(icone_mudar_foto_perfil)

            caminhoFotoAceita = caminhoFoto
        }
    }


    fun carregaFoto(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, EditaPerfilActivity.REQUEST_CAMERA)
    }



}