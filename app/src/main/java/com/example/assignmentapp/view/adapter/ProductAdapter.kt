package com.example.assignmentapp.view.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentapp.R
import com.example.assignmentapp.model.ProductModel
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val context: Context,
    private val listItemClickListener: ListItemClickListener
    )
    : ListAdapter<ProductModel.ProductModelItem, RecyclerView.ViewHolder>(ListItemCallback()){

    fun filterList(filterList: ArrayList<ProductModel.ProductModelItem>){

    }

    interface ListItemClickListener {
        fun onItemClick(
            item: ProductModel.ProductModelItem,
            position: Int
        )
    }

    class ListItemCallback: DiffUtil.ItemCallback<ProductModel.ProductModelItem>(){
        override fun areItemsTheSame(
            oldItem: ProductModel.ProductModelItem, newItem: ProductModel.ProductModelItem
        ): Boolean { return oldItem == newItem }

        override fun areContentsTheSame(
            oldItem: ProductModel.ProductModelItem, newItem: ProductModel.ProductModelItem
        ): Boolean { return oldItem == newItem }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_product, parent, false)
        return ListenItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        (holder as ListenItemViewHolder).bind(currentItem, position)
    }


    inner class ListenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivProduct: ImageView = itemView.findViewById(R.id.iv_product)
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        var tvOldPrice: TextView = itemView.findViewById(R.id.tv_oldPrice)
        var tvCategory: TextView = itemView.findViewById(R.id.tv_category)

        fun bind(item: ProductModel.ProductModelItem, position: Int) {

            Picasso.with(context).load(item.image).into(ivProduct)
            tvName.text = item.title
            tvPrice.text = item.price.toString()
            tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            tvCategory.text = item.category

            itemView.setOnClickListener {
                listItemClickListener.onItemClick(
                    item,
                    adapterPosition
                )
            }
        }
    }
}