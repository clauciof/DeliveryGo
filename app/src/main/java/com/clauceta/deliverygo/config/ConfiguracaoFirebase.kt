package com.clauceta.deliverygo.config

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConfiguracaoFirebase {

    companion object {
        private var referenciafirebase = FirebaseDatabase.getInstance().getReference("Usuario")

        fun getFirebase(): DatabaseReference{

            if(referenciafirebase == null){
                referenciafirebase = FirebaseDatabase.getInstance().getReference("Usuario")
            }


            return referenciafirebase
        }
    }


}