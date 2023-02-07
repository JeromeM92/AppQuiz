package com.example.appquiz.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appquiz.data.model.QuestionEntity
import com.example.appquiz.data.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question_table")
    /*Un Flow est un observable qui va observer ici, le resultat de la requete ci-dessus, il se met à jour automatiquement sans besoin de relancer la requete*/
    fun getAllQuestions() : Flow<MutableList<QuestionEntity>>

    @Query("DELETE FROM question_table")
    fun deleteAll()

    @Query("SELECT count(*) FROM question_table")
    fun count(): Int

    @Insert
    fun insertQuestion(question: QuestionEntity)
}