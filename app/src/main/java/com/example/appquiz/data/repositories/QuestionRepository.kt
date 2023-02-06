package com.example.appquiz.data.repositories

import com.example.appquiz.data.dao.QuestionDao
import com.example.appquiz.data.model.QuestionEntity
import kotlinx.coroutines.flow.Flow

class QuestionRepository(private val questionDao: QuestionDao) {


    fun getAllQuestions(): Flow<MutableList<QuestionEntity>> {
        return questionDao.getAllQuestions()
    }

    fun insertQuestion(question: QuestionEntity) {
        questionDao.insertQuestion(question)
    }
}