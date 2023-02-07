package com.example.appquiz.domain

import com.example.appquiz.commons.SessionManager
import com.example.appquiz.data.dao.UserDao
import com.example.appquiz.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConnectUseCase(val username: String, val pass: String, userDao: UserDao): IFetchUseCase<Nothing> {
    val userRepository: UserRepository = UserRepository(userDao)

    override suspend fun execute(): Result<Nothing> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                var user = userRepository.getUser(username, pass)
                SessionManager.user = user
                Result.Success(null)
            } catch (ex: Exception) {
                Result.Failure<Nothing>(Throwable(ex.message))
            }
        }
    }

}