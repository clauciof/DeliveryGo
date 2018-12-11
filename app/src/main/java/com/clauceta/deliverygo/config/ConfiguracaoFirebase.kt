package com.clauceta.deliverygo.config

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConfiguracaoFirebase {

    companion object {
        private var referenciafirebaseUsuario = FirebaseDatabase.getInstance().getReference("Usuario")

        private var referenciafirebasePedido = FirebaseDatabase.getInstance().getReference("Pedido")

        fun getFirebaseUsuario(): DatabaseReference{

            if(referenciafirebaseUsuario == null){
                referenciafirebaseUsuario = FirebaseDatabase.getInstance().getReference("Usuario")
            }


            return referenciafirebaseUsuario
        }


        fun getFirebasePedido(): DatabaseReference{

            if(referenciafirebasePedido == null){
                referenciafirebasePedido = FirebaseDatabase.getInstance().getReference("Pedido")
            }


            return referenciafirebasePedido
        }
    }


}