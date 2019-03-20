package com.clauceta.deliverygo.activitys

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.clauceta.deliverygo.R
import com.clauceta.deliverygo.config.GlideApp
import com.clauceta.deliverygo.models.Pedido
import kotlinx.android.synthetic.main.item_lista_seus_pedidos.view.*

class PedidosAdapter(val context: Context, val pedido: List<Pedido>)
    : RecyclerView.Adapter<PedidosAdapter.ViewHolder>() {

    var itemClickListener: ((index: Int) -> Unit)? = null

    fun setOnItemClickListener(clique: ((index: Int) -> Unit)){
        this.itemClickListener = clique
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_seus_pedidos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pedido.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(context, pedido[position], position)
        Toast.makeText(context,pedido[position].getPedido_descricao(), Toast.LENGTH_SHORT).show()


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(context: Context, pedido: Pedido, position: Int) {
            itemView.descricao_pedido.text = pedido.getPedido_descricao()
            itemView.origem.text = "Origem: "+pedido.getPedido_origem_rua()
            itemView.destino.text = "Destindo: "+pedido.getPedido_destino_rua()

        }

    }

}