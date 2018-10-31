package com.clauceta.deliverygo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.pagina_login.*

class Login: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_login)

        botao_login.setOnClickListener(){

            val paginaPrincipal = Intent(this, MainActivity::class.java)
            startActivity(paginaPrincipal)

        }

        botao_cadastre.setOnClickListener(){

            val pagina_cadastro = Intent(this, Cadastro::class.java)
            startActivity(pagina_cadastro)

        }
    }
}