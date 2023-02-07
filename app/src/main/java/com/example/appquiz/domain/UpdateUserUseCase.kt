package com.example.appquiz.domain

import com.example.appquiz.data.dao.UserDao
import com.example.appquiz.data.model.UserEntity
import com.example.appquiz.data.repositories.UserRepository

class UpdateUserUseCase(val user: UserEntity, userDao: UserDao): IFetchUseCase<Nothing> {
    val userRepository: UserRepository = UserRepository(userDao)
    override suspend fun execute(): Result<Nothing> {
        return try {
            userRepository.updateUser(user)
            Result.Success(null)
        } catch (ex: Exception) {
            Result.Failure(Throwable(ex.message))
        }
    }
}