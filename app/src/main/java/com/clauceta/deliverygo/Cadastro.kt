package com.clauceta.deliverygo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.pagina_cadastro.*
import kotlinx.android.synthetic.main.pagina_login.*

class Cadastro : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_cadastro)

        botao_sign_up.setOnClickListener(){

            val Nome: String = campo_nickname.text.toString()
            val email: String =  campo_email_cadastro.text.toString()
            val senha: String =  campo_password.text.toString()

            //Validaçao de campos
            if(Nome.isNullOrEmpty() || email.isNullOrEmpty() || senha.isNullOrEmpty()){
                //nao faz cadastro
                Toast.makeText(this, "Campos devem ser preenchidos", Toast.LENGTH_LONG).show()

            }else{
                //faz cadastro
                myAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        //Registration OK Faz Cadastro
                        Log.i("create user", "sucesso")
                        Toast.makeText(this, "Usuario cadastrado", Toast.LENGTH_LONG).show()
                    } else {
                        //Registration error
                        Log.i("create user", "erro")
                        Toast.makeText(this, "Erro ao cadastrar usuario", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }


    }



}