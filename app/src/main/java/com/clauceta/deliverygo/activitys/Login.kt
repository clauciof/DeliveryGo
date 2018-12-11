package com.clauceta.deliverygo.activitys
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.config.ConfiguracaoFirebase
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.pagina_login.*

class Login: AppCompatActivity() {

    private var firebaseauth = FirebaseAuth.getInstance()
    private var referenciafirebase = FirebaseDatabase.getInstance().reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_login)


        botao_login.setOnClickListener(){

            val email = campo_email.text.toString()
            val senha = campo_senha.text.toString()


            //Validaçao de campos
            if(email.isNullOrEmpty() || senha.isNullOrEmpty()){
                //nao faz login
                Toast.makeText(this, "Campos devem ser preenchidos", Toast.LENGTH_LONG).show()

            }else{
                //faz login
               firebaseauth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        //Registration OK Faz Cadastro
                        Log.i("login user", "sucesso ao fazer login")
                        Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_LONG).show()

                        val paginaPrincipal = Intent(this, MainActivity::class.java)
                        startActivity(paginaPrincipal)

                    } else {
                        //Registration error
                        Log.i(" login user", "erro")
                        Toast.makeText(this, "Erro ao fazer login", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        botao_cadastre.setOnClickListener(){

            val pagina_cadastro = Intent(this, Cadastro::class.java)
            startActivity(pagina_cadastro)
        }
    }
}