package com.example.appquiz.domain

import com.example.appquiz.data.dao.QuestionDao
import com.example.appquiz.data.model.QuestionEntity
import com.example.appquiz.data.repositories.QuestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class GetQuestionToDoUseCase(dao: QuestionDao): IFetchUseCase<MutableList<QuestionEntity>> {
    val questionRepository: QuestionRepository = QuestionRepository(dao)
    override suspend fun execute(): Result<MutableList<QuestionEntity>> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val questions = questionRepository.getAllQuestions().first()
                Result.Success(questions)
            } catch (ex: Exception) {
                Result.Failure(Throwable(ex.message))
            }
        }
    }


}