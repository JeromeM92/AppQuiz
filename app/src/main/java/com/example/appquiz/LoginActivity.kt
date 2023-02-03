package com.example.appquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appquiz.databinding.ActivityLoginBinding
import com.example.appquiz.presentation.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.loginResult.observe(this, Observer { processLogin(it) })

        binding.validate.setOnClickListener {
            loginViewModel.login(
                binding.editUserName.text.toString(),
                binding.editPassword.text.toString()
            )
        }

    }

    private fun processLogin(isAuthenticated: Boolean) {
        if (isAuthenticated) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else {
            Toast.makeText(this,  "Mauvais identifiant", Toast.LENGTH_LONG)
        }
    }
}