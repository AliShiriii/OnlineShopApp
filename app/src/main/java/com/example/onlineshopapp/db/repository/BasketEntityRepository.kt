package com.example.onlineshopapp.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.OnlineShopDataBase
import com.example.onlineshopapp.db.dao.BasketDao
import com.example.onlineshopapp.db.models.BasketEntity

class BasketEntityRepository(application: Application) {

    private var basketDao: BasketDao
    private var liveDataList: LiveData<List<BasketEntity>>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        basketDao = database.basketDao()
        liveDataList = basketDao.getAllLive()
    }

    suspend fun getAllBasket(): List<BasketEntity> {
        return basketDao.getAll()
    }

    fun getAllLive(): LiveData<List<BasketEntity>> {
        return liveDataList
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