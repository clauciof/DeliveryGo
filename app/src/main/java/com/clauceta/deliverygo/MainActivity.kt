package com.clauceta.deliverygo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance()
    var DBreferencia = database.getReference("usuario")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuario = Usuario()

        usuario.setnome("Claucio Filho")
        usuario.setemail("claucio@gmail.com")
        usuario.setid(0)
        usuario.setsenha("tsaftsfatsa")


        DBreferencia.child(usuario.getid().toString()).setValue(usuario)

    }


}
