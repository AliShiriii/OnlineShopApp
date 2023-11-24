package com.example.onlineshopapp.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.OnlineShopDataBase
import com.example.onlineshopapp.db.dao.UserDao
import com.example.onlineshopapp.db.models.UserEntity

class UserEntityRepository(application: Application) {

    private var userDao: UserDao
    private lateinit var currentUser: LiveData<UserEntity>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        userDao = database.userDao()
        currentUser = userDao.get()
    }

    suspend fun insert(userEntity: UserEntity) {
        deleteAll()
        userDao.insert(userEntity)
    }

    suspend fun update(userEntity: UserEntity) {
        userDao.update(userEntity)
    }

    suspend fun delete(userEntity: UserEntity) {
        userDao.delete(userEntity)
    }

    suspend fun deleteAll() {
        return userDao.deleteAll()
    }
}