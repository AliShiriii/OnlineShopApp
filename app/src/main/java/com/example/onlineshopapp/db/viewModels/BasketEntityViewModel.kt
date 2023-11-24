package com.example.onlineshopapp.db.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.repository.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = BasketEntityRepository(application)
    private var currentUser = repository.getAllBasket()

    suspend fun insert(basketEntity: BasketEntity) {
        repository.insert(basketEntity)
    }

    suspend fun update(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.update(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        repository.delete(basketEntity)
    }

    suspend fun deleteAll() {
        return repository.deleteAll()
    }

    fun getAllBasketList(): LiveData<List<BasketEntity>> {
        return currentUser
    }
}