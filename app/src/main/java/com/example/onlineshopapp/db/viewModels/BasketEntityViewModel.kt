package com.example.onlineshopapp.db.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.onlineshopapp.db.models.BasketEntity
import com.example.onlineshopapp.db.repository.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = BasketEntityRepository(application)

    private suspend fun insert(basketEntity: BasketEntity) {
        repository.insert(basketEntity)
    }

    private suspend fun update(basketEntity: BasketEntity) {
        if (basketEntity.id <= 0) return
        repository.update(basketEntity)
    }

    suspend fun addTooBasket(basketEntity: BasketEntity) {

        val allBasketList = repository.getAllBasket()

        if (allBasketList != null && allBasketList.any { x ->
                x.productId == basketEntity.productId &&
                        x.colorId == basketEntity.colorId
                        && x.sizeId == basketEntity.sizeId
            }) {
            val oldBasket =
                allBasketList.first { x ->
                    x.productId == basketEntity.productId &&
                            x.colorId == basketEntity.colorId
                            && x.sizeId == basketEntity.sizeId
                }

            oldBasket.quantity++
            update(oldBasket)
        } else {


        }
    }

    suspend fun delete(basketEntity: BasketEntity) {
        repository.delete(basketEntity)
    }

    suspend fun deleteAll() {
        return repository.deleteAll()
    }

    suspend fun getAllBasketList(): List<BasketEntity> {
        return repository.getAllBasket()
    }
}