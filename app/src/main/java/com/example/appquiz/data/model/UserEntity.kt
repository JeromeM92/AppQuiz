package com.example.appquiz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userName: String,
    val password: String,
    var score: Int
)
