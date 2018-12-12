package com.clauceta.deliverygo.activitys

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.models.Pedido
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.seus_pedidos_fragment.*

class FragmentSeusPedidos: Fragment() {

    // retorna id unico do usuario logado
    var firebaseauth = FirebaseAuth.getInstance().currentUser!!.uid

    // referencia para a classe usuario do usuario logado
    var pedidosUsuario = FirebaseDatabase.getInstance().reference.child("Pedido")

    var pedidosLista: MutableList<Pedido> = mutableListOf()

    companion object {

        private val ARG_LIST = "arg_list"

        fun newInstance(list: ArrayList<Pedido>) =
                FragmentSeusPedidos().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_LIST, list)
                    }
                }
    }

    //var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.seus_pedidos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pedidos = arguments?.getSerializable(ARG_LIST) as ArrayList<Pedido>?
        if(pedidos == null){
            throw NullPointerException("Articles list can not be null")
        }

       activity?.let{ that ->
            val adapter = PedidosAdapter(that, pedidos)
           /* adapter.setOnItemClickListener {position ->
                listener?.onFragmentInteraction(pedidos[position])
            }*/

            rvPedidos.adapter = adapter
            rvPedidos.layoutManager = LinearLayoutManager(that)

       }

    }

    /*override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is FragmentSeusPedidos.OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement NewsListFragment.OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun getPedidosList(): MutableList<Pedido>{
        val pedidos = arguments?.getSerializable(ARG_LIST) as ArrayList<Pedido>?
        if(pedidos == null){
            throw NullPointerException("Articles list can not be null")
        }
    }

        return pedidos

        // Listener para retornar pedidos do usuario da nuvem
        pedidosUsuario.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val snapshot: DataSnapshot = dataSnapshot.child("Pedido")
                val Children = snapshot.getChildren()

                for (dataSnapshot: DataSnapshot in Children){
                    if (dataSnapshot.child("id").value == firebaseauth){
                        var pedido = Pedido()
                        pedido.setPedido_descricao(dataSnapshot.child("pedido_descricao").value.toString())
                        pedido.setPedido_origem_rua(dataSnapshot.child("pedido_origem_rua").value.toString())
                        pedido.setPedido_destino_rua(dataSnapshot.child("pedido_destino_rua").value.toString())
                        pedidosLista.add(pedido)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return pedidosLista

    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(pedido: Pedido)
    }*/

}