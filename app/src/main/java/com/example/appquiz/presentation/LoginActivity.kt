package com.example.appquiz.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import com.example.appquiz.MainActivity
import com.example.appquiz.data.DataBase
import com.example.appquiz.data.model.QuestionEntity
import com.example.appquiz.data.model.UserEntity
import com.example.appquiz.data.repositories.QuestionRepository
import com.example.appquiz.data.repositories.UserRepository
import com.example.appquiz.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        populateDatabase()
        binding = ActivityLoginBinding.inflate(layoutInflater)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.loginResult.observe(this, Observer { processLogin(it) })
        setContentView(binding.root)
        binding.validate.setOnClickListener {
            loginViewModel.login(
                binding.editUserName.text.toString(),
                binding.editPassword.text.toString(),
                this
            )
        }

    }

    private fun processLogin(isAuthenticated: Boolean) {
        if (isAuthenticated) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else {
            Toast.makeText(this,  "Mauvais identifiant", Toast.LENGTH_LONG).show()
        }
    }

    private fun populateDatabase() {
        val questionRepository = QuestionRepository(DataBase.getDataBase(baseContext)!!.questionDao())
        val userRepository = UserRepository(DataBase.getDataBase(baseContext)!!.userDao())
        var questions: MutableList<QuestionEntity> = ArrayList()

        questions.add(QuestionEntity(0, "Quel est le seul pays qui possède un drapeau carré?", "La Suisse"))
        questions.add(QuestionEntity(0, "Quelle est la capitale de l'Ukraine?", "Kiev"))
        questions.add(QuestionEntity(0, "Depuis quelle année Kotlin est le langage officiel du développement sur Android?", "2019"))

        CoroutineScope(Dispatchers.IO).launch {
            questions.forEach{questionRepository.insertQuestion(it)}
            userRepository.insertUser(UserEntity(0, "JohnDoe", "4558", 0))
        }
    }
}