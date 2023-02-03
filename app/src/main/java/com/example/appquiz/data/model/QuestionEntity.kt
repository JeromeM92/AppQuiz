package com.example.appquiz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val text: String,
    val answer: String
)
