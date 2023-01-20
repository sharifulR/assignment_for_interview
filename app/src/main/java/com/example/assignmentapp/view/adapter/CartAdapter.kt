package com.example.assignmentapp.view.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentapp.R
import com.example.assignmentapp.model.CartModel
import com.squareup.picasso.Picasso

class CartAdapter(
    private val context: Context,
    private val listItemClickListener: CartAdapter.ListItemClickListener
): ListAdapter<CartModel, RecyclerView.ViewHolder>(CartAdapter.ListItemCallback()) {

    interface ListItemClickListener {
        fun onItemClick(
            item: CartModel,
            position: Int
        )
        fun addListener(
            quantity: Int,
            position: Int
        )
        fun subListener(
            quantity: Int,
            position: Int
        )
    }

    class ListItemCallback: DiffUtil.ItemCallback<CartModel>(){
        override fun areItemsTheSame(
            oldItem: CartModel, newItem: CartModel
        ): Boolean { return oldItem == newItem }

        override fun areContentsTheSame(
            oldItem: CartModel, newItem: CartModel
        ): Boolean { return oldItem == newItem }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_cart_layout, parent, false)
        return ListenItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        (holder as CartAdapter.ListenItemViewHolder).bind(currentItem, position)
    }

    inner class ListenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivCartImage: ImageView = itemView.findViewById(R.id.id_cartImage)
        val tvCartTitle: TextView = itemView.findViewById(R.id.id_cartTitle)
        val tvCartPrice: TextView = itemView.findViewById(R.id.id_cartPrice)
        val tvCartTotalPrice: TextView = itemView.findViewById(R.id.id_cartTotalPrice)

        val tvQuantity: TextView = itemView.findViewById(R.id.id_cartQuantity)
        var btn_Add: ImageButton = itemView.findViewById(R.id.cart_addButton)
        var btn_Sub: ImageButton = itemView.findViewById(R.id.id_cartSubButton)

        fun bind(item: CartModel, position: Int) {

            ivCartImage.setImageResource(item.image)
            tvCartTitle.text = item.name
            tvCartPrice.text = item.price.toString()
            tvCartTotalPrice.text = "${item.quantity * item.price}"
            tvQuantity.text = item.quantity.toString()

            itemView.setOnClickListener {
                listItemClickListener.onItemClick(
                    item,
                    adapterPosition
                )
            }
            btn_Add.setOnClickListener {
                listItemClickListener.addListener(item.quantity, adapterPosition)
            }
            btn_Sub.setOnClickListener {
                listItemClickListener.subListener(item.quantity, adapterPosition)
            }
        }
    }
}