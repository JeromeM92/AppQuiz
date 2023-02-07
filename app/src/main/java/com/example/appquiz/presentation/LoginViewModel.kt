package com.example.appquiz.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    val loginResult: MutableLiveData<Boolean> = MutableLiveData()

    fun login(username: String, password: String) {
        if (username == "Josh" && password == "0406") {
            loginResult.postValue(true)
        } else {
            loginResult.postValue(false)
        }
    }
}