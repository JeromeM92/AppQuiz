package com.example.appquiz.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.example.appquiz.domain.ConnectUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val loginResult: MutableLiveData<Boolean> = MutableLiveData()

    fun login(username: String, password: String, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            val connectResult = ConnectUseCase(username, password, Database.getDatabase(context)!!.userDao()).execute()
        if (connectResult is Result.Success) {
            loginResult.postValue(true)
        } else {
            loginResult.postValue(false)
        }
        }
    }
}