package com.clauceta.deliverygo

 open class Usuario{

     private var nome: String = "nome"
     private var email: String = "email"
     private var id: Int =0
     private var senha: String = "_senha"


     open fun setnome(nome: String){
         this.nome = nome
     }

     open fun setemail(email: String){
         this.email = email
     }

     open fun setid(id: Int){
         this.id = id
     }

     open fun setsenha(senha: String){
         this.senha = senha
     }

     open fun getnome(): String{
         return this.nome
     }

     open fun getid(): Int{
         return this.id
     }

     open fun getemail(): String{
        return this.email
     }

     open fun getsenha(): String{
         return this.senha
     }



}