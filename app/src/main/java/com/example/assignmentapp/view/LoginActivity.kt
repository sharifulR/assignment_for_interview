package com.example.assignmentapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.assignmentapp.databinding.ActivityLoginBinding
import com.example.assignmentapp.model.LoginRequest
import com.example.assignmentapp.network.NetworkResult
import com.example.assignmentapp.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this, ProductActivity::class.java))
        viewModel.loginResponseLiveData.observe(this, Observer {
            when(it){
                is NetworkResult.Success ->{
                    //startActivity(Intent(this, ProductActivity::class.java))
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, "Login failed!, please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            val username = binding.etUserName.text.trim().toString()
            val password = binding.etPassword.text.trim().toString()
            if (username.isNotEmpty() || password.isNotEmpty()){
                    viewModel.login(LoginRequest(username, password))
            }else{
                Toast.makeText(this, "Password and username is required",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}