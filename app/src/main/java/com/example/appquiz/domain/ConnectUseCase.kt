package com.example.appquiz.domain

import com.example.appquiz.data.dao.UserDao
import com.example.appquiz.data.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConnectUseCase(val username: String, val pass: String, userDao: UserDao): IFetchUseCase<Nothing> {
    val userRepository: UserRepository = UserRepository(userDao)

    override suspend fun execute(): Result<Nothing> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                userRepository.getUser(username, pass)
                Result.Success(null)
            } catch (ex: Exception) {
                Result.Failure<Nothing>(Throwable(ex.message))
            }
        }
    }

}