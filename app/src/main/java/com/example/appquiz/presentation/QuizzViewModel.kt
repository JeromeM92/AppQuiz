package com.example.appquiz.presentation


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appquiz.data.DataBase
import com.example.appquiz.data.model.QuestionEntity
import com.example.appquiz.data.model.UserEntity
import com.example.appquiz.domain.GetQuestionToDoUseCase
import com.example.appquiz.domain.UpdateUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizzViewModel: ViewModel() {
    val questions: MutableLiveData<MutableList<QuestionEntity>> = MutableLiveData()

    fun getAllQuestions(context: Context) {
        /*Pourquoi Main, parce que resultat on veut s'en servir sur thread principal, et la fcntion suspsend dans result doit etre exucter sur une coroutine*/
        CoroutineScope(Dispatchers.Main).launch {
            val questionResult = GetQuestionToDoUseCase(DataBase.getDataBase(context)!!.questionDao()).execute()
            /*is en kotlin remplace le instanceOf de JAVA, il verifie si ici, questionResult est une instance de Result*/
            if (questionResult is com.example.appquiz.domain.Result.Success) {
                questions.postValue(questionResult.value)
            }
        }
    }

    fun updateUser(user: UserEntity, context: Context) {
        CoroutineScope(Dispatchers.IO).launch{
            UpdateUserUseCase(user, DataBase.getDataBase(context)!!.userDao()).execute()
        }
    }
}