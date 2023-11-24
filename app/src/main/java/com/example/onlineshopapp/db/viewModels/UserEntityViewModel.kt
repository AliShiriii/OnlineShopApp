package com.example.onlineshopapp.db.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.models.UserEntity
import com.example.onlineshopapp.db.repository.UserEntityRepository


class UserEntityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = UserEntityRepository(application)
    private var currentUser = repository.getCurrentUser()

    suspend fun insert(userEntity: UserEntity) {
        repository.insert(userEntity)
    }

    suspend fun update(userEntity: UserEntity) {
        if (userEntity.id <= 0) return
        repository.update(userEntity)
    }

    suspend fun delete(userEntity: UserEntity) {
        repository.delete(userEntity)
    }

    suspend fun deleteAll() {
        return repository.deleteAll()
    }

     fun getCurrentUser(): LiveData<UserEntity> {
        return currentUser
    }
}