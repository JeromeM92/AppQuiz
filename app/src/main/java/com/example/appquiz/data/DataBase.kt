package com.example.appquiz.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appquiz.data.dao.QuestionDao
import com.example.appquiz.data.dao.UserDao
import com.example.appquiz.data.model.QuestionEntity
import com.example.appquiz.data.model.UserEntity


@androidx.room.Database(
    entities = [UserEntity::class, QuestionEntity::class],
    version = 1,
    exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun userDao(): UserDao

    //Permet d'avoir un singleton = classe unique dans l'application
    companion object {

        var INSTANCE: DataBase? = null

        fun getDataBase(context: Context): DataBase? {
            if(INSTANCE == null) {
                synchronized(DataBase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DataBase::class.java,
                            "database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}