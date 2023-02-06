package com.example.appquiz.data.repositories

import com.example.appquiz.data.dao.UserDao
import com.example.appquiz.data.model.UserEntity

class UserRepository(private val userDao: UserDao) {

    fun getUser(username: String, password: String): UserEntity {
        return userDao.getUser(username, password)
    }

    fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    fun updateUser(user: UserEntity) {
        userDao.update(user)
    }
}