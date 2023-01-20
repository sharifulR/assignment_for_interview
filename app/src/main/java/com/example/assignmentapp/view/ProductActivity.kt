package com.example.assignmentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentapp.databinding.ActivityProductBinding
import com.example.assignmentapp.model.ProductModel
import com.example.assignmentapp.network.NetworkResult
import com.example.assignmentapp.view.adapter.ProductAdapter
import com.example.assignmentapp.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity(), ProductAdapter.ListItemClickListener {

    private var _binding: ActivityProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProductViewModel>()

    private var products = mutableListOf<ProductModel.ProductModelItem>()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var layoutManager : LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getProducts()

        setupRecyclerView()
        setLiveDataListener()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(this, this)
        layoutManager = GridLayoutManager(this, 2)
        binding.rvProduct.layoutManager = layoutManager
        binding.rvProduct.itemAnimator = DefaultItemAnimator()
        binding.rvProduct.adapter = productAdapter
    }

    private fun setLiveDataListener() {
        viewModel.productResponseLiveData.observe(this, Observer {
            when(it){
                is NetworkResult.Success->{
                    if (it.data !=  null){
                        products.addAll(it.data)
                        productAdapter.submitList(products)
                        productAdapter.notifyDataSetChanged()
                    }
                }
                is NetworkResult.Error -> {
                   Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT)
                       .show()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(item: ProductModel.ProductModelItem, position: Int) {
        //TODO("Not yet implemented")
    }
}