package com.clauceta.deliverygo.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.config.ConfiguracaoFirebase
import com.clauceta.deliverygo.models.Usuario
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.pagina_cadastro.*

class Cadastro : AppCompatActivity() {

    var firebaseauth = FirebaseAuth.getInstance()
    private var referenciafirebase = FirebaseDatabase.getInstance().reference

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
                firebaseauth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            registrausuario(Nome, email, senha, task.getResult()!!.user.uid)
                            finish()

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

    fun registrausuario(Nome: String, email: String, senha: String, id: String){

        referenciafirebase = ConfiguracaoFirebase.getFirebaseUsuario()

        val usuario = Usuario()
        usuario.setnome(Nome)
        usuario.setemail(email)
        usuario.setid(id)
        usuario.setsenha(senha)

        referenciafirebase.child(usuario.getid()).setValue(usuario)
    }



}