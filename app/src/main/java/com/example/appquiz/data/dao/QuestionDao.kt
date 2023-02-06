package com.example.appquiz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appquiz.data.model.QuestionEntity
import com.example.appquiz.data.model.UserEntity
import java.util.concurrent.Flow

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question_table")
    fun getAllQuestions() : Flow<MutableList<QuestionEntity>>

    @Query("DELETE FROM question_table")
    fun deleteAll()

    @Query("SELECT count(*) FROM question_table")
    fun count(): Int

    @Insert
    fun insertQuestion(question: QuestionEntity)
}