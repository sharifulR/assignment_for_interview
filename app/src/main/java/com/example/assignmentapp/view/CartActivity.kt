package com.example.assignmentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentapp.R
import com.example.assignmentapp.databinding.ActivityCartBinding
import com.example.assignmentapp.model.CartModel
import com.example.assignmentapp.view.adapter.CartAdapter

class CartActivity : AppCompatActivity(), CartAdapter.ListItemClickListener {

    private var _binding: ActivityCartBinding? = null
    private val binding get() = _binding!!

    val data = ArrayList<CartModel>()
    lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        binding.rvCart.apply {
            this.layoutManager = LinearLayoutManager(this@CartActivity)
        }
        adapter = CartAdapter(this, this)

        for (i in 1..20) {
            data.add(CartModel(
                "Lays Classic Family Chips",
                R.drawable.product_img, 20.0, 0.00, 5+i))
        }

        adapter.submitList(data)
        binding.rvCart.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(item: CartModel, position: Int) {

    }

    override fun addListener(quantity: Int, position: Int) {
        data[position].quantity += 1
        adapter.notifyDataSetChanged()
    }

    override fun subListener(quantity: Int, position: Int) {
        if (data[position].quantity == 0){
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show()
        }else{
            data[position].quantity -= 1
            adapter.notifyDataSetChanged()
        }
    }
}