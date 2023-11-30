package com.example.onlineshopapp.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.OnlineShopDataBase
import com.example.onlineshopapp.db.dao.BasketDao
import com.example.onlineshopapp.db.models.BasketEntity

class BasketEntityRepository(application: Application) {

    private var basketDao: BasketDao

    init {
        val database = OnlineShopDataBase.getInstance(application)
        basketDao = database.basketDao()
    }

    suspend fun getAllBasket(): List<BasketEntity> {
        return basketDao.getAll()
    }

    suspend fun insert(basketEntity: BasketEntity) {
        deleteAll()
        basketDao.insert(basketEntity)
    }

    suspend fun update(basketEntity: BasketEntity) {
        basketDao.update(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        basketDao.delete(basketEntity)
    }

    suspend fun deleteAll() {
        return basketDao.deleteAll()
    }
}