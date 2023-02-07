package com.example.appquiz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appquiz.data.model.UserEntity


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUserById(id: Int) : UserEntity

    @Query("SELECT * FROM user_table WHERE username = :username AND pass = :pass")
    fun getUser(username: String, pass: String) : UserEntity

    @Update
    fun update(user: UserEntity)

    @Insert
    fun insertUser(user: UserEntity)
}