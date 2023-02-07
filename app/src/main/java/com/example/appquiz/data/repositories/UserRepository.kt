package com.example.appquiz.data.repositories

import com.example.appquiz.data.dao.UserDao
import com.example.appquiz.data.model.UserEntity

class UserRepository(private val userDao: UserDao) {

    fun getUser(username: String, pass: String): UserEntity {
        return userDao.getUser(username, pass)
    }

    fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    fun updateUser(user: UserEntity) {
        userDao.update(user)
    }
}